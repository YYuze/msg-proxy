package com.yyuze.processor;

/**
 * Author: yyuze
 * Created: 2018/9/11
 */
public class Demo2LocalTest1 extends DemoBaseMsgProcessor {

    @Override
    public String formatResourceData(String data){
        //对原始数据进行处理
        return data != null ? data +" formatted (this is demo 1)": "data not find";
    }

}
