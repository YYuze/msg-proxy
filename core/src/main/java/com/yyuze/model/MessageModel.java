package com.yyuze.model;

import com.yyuze.entity.Route;

/**
 * Author: yyuze
 * Created: 2018/9/14
 */

public class MessageModel {

    private Route route;

    private String data;

    public int transferedCount;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTransferedCount() {
        return transferedCount;
    }

    public void setTransferedCount(int transferedCount) {
        this.transferedCount = transferedCount;
    }
}
