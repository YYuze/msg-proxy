package com.yyuze.processor;

/**
 * Author: yyuze
 * Created: 2018/9/21
 */
public class Demo2LocalTest2 extends DemoBaseMsgProcessor{

    @Override
    public String formatResourceData(String data) {
        //对原始数据进行处理
        return data != null ? data +" formatted (this is demo 2)": "data not find";
    }
}
