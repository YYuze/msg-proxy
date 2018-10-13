package com.yyuze.manager.impl;

import com.yyuze.entity.Jar;
import com.yyuze.entity.Route;
import com.yyuze.manager.TransmitManager;
import com.yyuze.model.MessageModel;
import com.yyuze.mq.MessageQueue;
import com.yyuze.processor.MsgProcessor;
import com.yyuze.repository.JarRepository;
import com.yyuze.repository.RouteRepository;
import com.yyuze.util.JsonUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;

/**
 * Author: yyuze
 * Created: 2018/8/31
 */
@Component
public class TransmitManagerImp implements TransmitManager {

    private JarRepository jarRepository;

    private RouteRepository routeRepository;

    private MessageQueue messageQueue;

    //每个type对应一个classloader
    private HashMap<String, URLClassLoader> classLoaderMap;

    //每个type对应一个处理器processor
    private HashMap<String, MsgProcessor> processorMap;

    //每个token对应一个处理类型type
    private HashMap<String, String> typeMap;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private boolean isCorrect = true;

    @Autowired
    public TransmitManagerImp(JarRepository jarRepository, RouteRepository routeRepository, MessageQueue messageQueue) {
        this.jarRepository = jarRepository;
        this.routeRepository = routeRepository;
        this.messageQueue = messageQueue;
        this.load();
    }

    @Override
    public boolean load() {
        this.isCorrect = true;
        this.classLoaderMap = new HashMap<>();
        this.processorMap = new HashMap<>();
        this.typeMap = new HashMap<>();
        this.instanceClassLoaders();
        this.instanceProcessors();
        this.loadTypeMap();
        return this.isCorrect;
    }


    /**
     * 加载classloader
     * classloaderMap中存放以type为检索条件的classloader
     */
    private void instanceClassLoaders() {
        try {
            List<Jar> jars = this.jarRepository.loadProcessJars();
            for (Jar jar : jars) {
                URL url = new URL(jar.getJarFilePath());
                URLClassLoader classLoader = new URLClassLoader(new URL[]{url}, this.getClass().getClassLoader());
                if (this.classLoaderMap.containsKey(jar.getType())) {
                    this.classLoaderMap.replace(jar.getType(), classLoader);
                }
                else {
                    this.classLoaderMap.put(jar.getType(), classLoader);
                }
            }
        } catch (MalformedURLException e) {
            this.isCorrect = false;
            logger.error(e.getCause().toString());
        }
    }

    /**
     * 加载处理类实例
     * processorMap中存放以type为检索条件的处理类实例对象
     */
    private void instanceProcessors() {
        this.classLoaderMap.forEach((type, proxyClassLoader) -> {
            try {
                Jar jar = this.jarRepository.getJarByType(type).get(0);
                Class clazz = proxyClassLoader.loadClass(jar.getClassName());
                MsgProcessor msgProcessor = (MsgProcessor) clazz.newInstance();
                processorMap.put(jar.getType(), msgProcessor);
                proxyClassLoader.close();
            } catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                this.isCorrect = false;
                logger.error(e.getCause().toString());
            }
        });
    }

    /**
     * 加载处理消息的类型
     * typeMap中存放以Route表的token为检索条件的type字段
     */
    private void loadTypeMap() {
        List<Route> routes = this.routeRepository.loadRoutes();
        for (Route route : routes) {
            this.typeMap.put(route.getToken(), route.getType());
        }
    }

    /**
     * 调用处理实例中的process方法，处理完json对象后将其放入mq
     */
    @Override
    public void addToMessageQueue(JSONObject json) throws Exception {
        String tokenStr = (String) json.get("token");
        String[] tokens = tokenStr.split(",");
        for (String token : tokens) {
            if (!this.typeMap.containsKey(token)) {
                throw new Exception("token error: " + token);
            }
            else {
                String type = this.typeMap.get(token);
                JSONObject pJson = new JSONObject();
                pJson.put("jar", JsonUtil.toJson(this.jarRepository.getJarByType(type)));
                pJson.put("route",JsonUtil.toJson(this.routeRepository.getRouteByToken("token")));
                pJson.put("data",json.get("data").toString());
                MsgProcessor processor = this.processorMap.get(type);
                MessageModel message = processor.process(json);
                this.messageQueue.pushToMQ(message);
            }
        }
    }
}
