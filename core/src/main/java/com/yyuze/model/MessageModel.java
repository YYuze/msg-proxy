package com.yyuze.model;

import com.yyuze.entity.Route;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: yyuze
 * Created: 2018/9/14
 */
@Getter
@Setter
public class MessageModel {

    private Route route;

    private String data;

    public int transferedCount;


}
