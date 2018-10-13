package com.yyuze.entity;

import javax.persistence.*;

/**
 * Author: yyuze
 * Created: 2018/9/30
 */

@Entity
@Table(name = "overflowed_message")
public class OverflowedMessage {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "route_token")
    private String routeToken;

    @Column(name = "transfer_data")
    private String data;

    @Column(name = "is_loaded")
    private int is_loaded = 0;

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

    public int getIs_loaded() {
        return is_loaded;
    }

    public void setIs_loaded(int is_loaded) {
        this.is_loaded = is_loaded;
    }
}
