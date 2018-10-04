package com.yyuze.processor;

import com.yyuze.model.MessageModel;
import org.json.JSONObject;

/**
 * Author: yyuze
 * Created: 2018/9/11
 */
public interface MsgProcessor {

    /**
     *返回的EventModel对象中必须所有属性都有值
     */
    MessageModel process(JSONObject json) throws Exception;

}
