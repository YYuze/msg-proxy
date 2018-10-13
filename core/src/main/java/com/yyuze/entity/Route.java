package com.yyuze.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Author: yyuze
 * Created: 2018/9/14
 */

/**
 * 该表以token为主键
 * 每个token对应一条转发路径
 */


@Entity
@Table(name = "Route")
public class Route {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "token")
    private String token;

    @Column(name = "type")
    private String type;

    @Column(name = "request_url")
    private String requestUrl;

    @Column(name = "request_token")
    private String requestToken;

    @Column(name = "request_method")
    private String requestMethod;

    /**
     * 用 %&&% 分隔每个头，例如：
     * Content-Type: text/plain;charset=UTF-8%&&%Connection: keep-alive
     */
    @Column(name = "request_header_str")
    private String requestHeaderStr;

    @Column(name = "request_success_flag")
    private String requestSuccessFlag;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestHeaderStr() {
        return requestHeaderStr;
    }

    public void setRequestHeaderStr(String requestHeaderStr) {
        this.requestHeaderStr = requestHeaderStr;
    }

    public String getRequestSuccessFlag() {
        return requestSuccessFlag;
    }

    public void setRequestSuccessFlag(String requestSuccessFlag) {
        this.requestSuccessFlag = requestSuccessFlag;
    }
}
