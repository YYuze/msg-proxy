package com.yyuze.processor;

/**
 * Author: yyuze
 * Created: 2018/9/25
 */
public class Default extends DemoBaseMsgProcessor{

    @Override
    public String formatResourceData(String data){
        //直接转发原始请求的数据
        return data;
    }
}
