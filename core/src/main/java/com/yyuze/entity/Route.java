package com.yyuze.entity;

import lombok.Getter;
import lombok.Setter;
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

@Getter
@Setter
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
}
