package com.yyuze.processor;

import com.yyuze.entity.Route;
import com.yyuze.model.MessageModel;
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

    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //本地环境
    private static String DB_URL = "jdbc:mysql://localhost:3306/webhook_agent";
    private static String USER = "root";
    private static String PASS = "113275";

    /**
     * 与proxy交互的方法
     */
    @Override
    public MessageModel process(JSONObject json) throws Exception {
        String token = json.get("token").toString();
        String resourceData = json.get("data").toString();
        Route route = getRouteByToken(token);
        String template = this.formatResourceData(resourceData);
        MessageModel event = new MessageModel();
        event.setRoute(route);
        event.setData(template);
        return event;
    }

    /**
     * 根据token(Route的主键）获取Route对象
     */
    public Route getRouteByToken(String token) throws Exception {
        Route route = new Route();
        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        String sql = "select * from route where token='" + token + "'";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            route.setToken(rs.getString("token"));
            route.setType(rs.getString("type"));
            route.setRequestUrl(rs.getString("request_url"));
            route.setRequestToken(rs.getString("request_token"));
            route.setRequestMethod(rs.getString("request_method"));
            route.setRequestHeaderStr(rs.getString("request_header_str"));
            route.setRequestSuccessFlag(rs.getString("request_success_flag"));
        }
        rs.close();
        stmt.close();
        conn.close();
        return route;
    }

    /**
     *格式化原始数据时需要实现的方法
     */
    public abstract String formatResourceData(String data) ;
}
