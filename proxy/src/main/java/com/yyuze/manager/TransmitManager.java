package com.yyuze.manager;

import com.yyuze.model.MessageModel;
import org.json.JSONObject;

import java.util.List;

/**
 * Author: yyuze
 * Created: 2018/8/31
 */
public interface TransmitManager {

    boolean load();

    void addToMessageQueue(JSONObject json) throws Exception;

}
