package com.yyuze.controller;

import com.yyuze.entity.Jar;
import com.yyuze.entity.Route;
import com.yyuze.manager.ConfigureManager;
import com.yyuze.model.ResponseModel;
import com.yyuze.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Author: yyuze
 * Created: 2018/9/17
 */

@RestController
@RequestMapping("/configure")
public class ConfigureController {

    @Autowired
    private ConfigureManager configureManager;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ResponseModel responseModel;

    private String type = null;

    @RequestMapping("/setType")
    @ResponseBody
    public String setJarType(HttpServletRequest request, HttpServletResponse response){
        this.type = request.getParameter("type");
        String target = request.getParameter("target");
        try {
            response.sendRedirect("/redirect?target="+target);
            this.responseModel = new ResponseModel(ResponseModel.SUCCESS,"");
        } catch (IOException e) {
            this.responseModel = new ResponseModel(ResponseModel.FAILED,e.getMessage());
            this.logger.error(e.getMessage());
        }
        return JsonUtil.toJson(this.responseModel);
    }

    @RequestMapping("/jar/upload")
    @ResponseBody
    public String uploadJar(HttpServletRequest request) {
        try {
            this.configureManager.uploadJar(request.getInputStream());
            this.responseModel = new ResponseModel(ResponseModel.SUCCESS, "");
        } catch (IOException e) {
            this.responseModel = new ResponseModel(ResponseModel.FAILED, e.getMessage());
            logger.warn(e.getMessage());
        }
        return JsonUtil.toJson(this.responseModel);
    }

    @RequestMapping("/jar/create")
    @ResponseBody
    public String createJar(HttpServletRequest request) {
        String body = request.getParameter("data");
        Jar jar = JsonUtil.fromJson(body, Jar.class);
        try {
            this.configureManager.createJar(jar);
            this.responseModel = new ResponseModel(ResponseModel.SUCCESS, "");
        } catch (Exception e) {
            this.responseModel = new ResponseModel(ResponseModel.FAILED, e.getMessage());
            this.logger.error(e.getMessage());
        }
        return JsonUtil.toJson(this.responseModel);
    }

    @RequestMapping("/jar/retrieve")
    @ResponseBody
    public String retrieveJar() {
        List<Jar> jarList;
        if(this.type!=null){
            jarList = this.configureManager.findJarsByType(this.type);
            this.type = null;
        }
        else{
            jarList = this.configureManager.findAllJars();
        }
        return JsonUtil.toJson(jarList);
    }

    @RequestMapping("/jar/update")
    @ResponseBody
    public String updateJar(HttpServletRequest request) {
        String body = request.getParameter("data");
        Jar jar = JsonUtil.fromJson(body, Jar.class);
        this.configureManager.updateJar(jar);
        this.responseModel = new ResponseModel(ResponseModel.SUCCESS, "");
        return JsonUtil.toJson(this.responseModel);
    }

    @RequestMapping("/jar/delete")
    @ResponseBody
    public String deleteJar(HttpServletRequest request) {
        String body = request.getParameter("data");
        Jar jar = JsonUtil.fromJson(body, Jar.class);
        this.configureManager.deleteJar(jar);
        this.responseModel = new ResponseModel(ResponseModel.SUCCESS, "");
        return JsonUtil.toJson(this.responseModel);
    }

    @RequestMapping("/route/create")
    @ResponseBody
    public String createRoute(HttpServletRequest request) {
        String body = request.getParameter("data");
        Route route = JsonUtil.fromJson(body, Route.class);
        this.configureManager.createRoute(route);
        this.responseModel = new ResponseModel(ResponseModel.SUCCESS, "");
        return JsonUtil.toJson(this.responseModel);
    }

    @RequestMapping("/route/retrieve")
    @ResponseBody
    public String retrieveRoute() {
        List<Route> routeList;
        if (this.type != null) {
            routeList = this.configureManager.findRoutesByType(this.type);
            this.type = null;
        }
        else {
            routeList = this.configureManager.findAllRoutes();
        }
        return JsonUtil.toJson(routeList);
    }

    @RequestMapping("/route/update")
    @ResponseBody
    public String updateRoute(HttpServletRequest request) {
        String body = request.getParameter("data");
        Route route = JsonUtil.fromJson(body, Route.class);
        this.configureManager.updateRoute(route);
        this.responseModel = new ResponseModel(ResponseModel.SUCCESS, "");
        return JsonUtil.toJson(responseModel);
    }

    @RequestMapping("/route/delete")
    @ResponseBody
    public String deleteRoute(HttpServletRequest request) {
        String body = request.getParameter("data");
        Route route = JsonUtil.fromJson(body, Route.class);
        this.configureManager.deleteRoute(route);
        this.responseModel = new ResponseModel(ResponseModel.SUCCESS, "");
        return JsonUtil.toJson(responseModel);
    }

}
