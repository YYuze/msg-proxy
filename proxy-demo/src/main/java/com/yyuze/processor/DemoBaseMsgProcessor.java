package com.yyuze.processor;

import com.yyuze.entity.Route;
import com.yyuze.model.MessageModel;
import com.yyuze.util.JsonUtil;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Author: yyuze
 * Created: 2018/9/21
 */

/**
 * 由于classloader无法识别跨包的抽象函数实现，因此需要每个包单独进行基类抽象
 */
public abstract class DemoBaseMsgProcessor implements MsgProcessor {

    /**
     * 与proxy交互的方法
     */
    @Override
    public MessageModel process(JSONObject json) throws Exception {
        String resourceData = json.get("data").toString();
        Route route = JsonUtil.fromJson(json.get("route").toString(),Route.class);
        String template = this.formatResourceData(resourceData);
        MessageModel event = new MessageModel();
        event.setRoute(route);
        event.setData(template);
        return event;
    }

    /**
     *格式化原始数据时需要实现的方法
     */
    public abstract String formatResourceData(String data) ;
}
