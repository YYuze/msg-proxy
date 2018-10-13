package com.yyuze.entity;

import javax.persistence.*;

/**
 * Author: yyuze
 * Created: 2018/9/25
 */


@Entity
@Table(name = "dropped_message")
public class DroppedMessage {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "route_token")
    private String routeToken;

    @Column(name = "transfer_data")
    private String data;

    @Column(name = "create_on")
    private String createOn;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRouteToken() {
        return routeToken;
    }

    public void setRouteToken(String routeToken) {
        this.routeToken = routeToken;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }
}
