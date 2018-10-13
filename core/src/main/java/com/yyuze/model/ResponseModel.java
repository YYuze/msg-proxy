package com.yyuze.model;

/**
 * Author: yyuze
 * Created: 2018/9/14
 */

public class ResponseModel {

    public static int SUCCESS = 1;

    public static int FAILED = 0;

    private boolean result;

    private String code;

    private String message;

    public ResponseModel(int type, String message) {
        switch (type) {
            case 1:
                this.result = true;
                this.code = "00";
                this.message = "OK " + message;
                break;
            case 0:
                this.result = false;
                this.code = "-1";
                this.message = "ERROR " + message;
                break;
        }
    }

    public static int getSUCCESS() {
        return SUCCESS;
    }

    public static void setSUCCESS(int SUCCESS) {
        ResponseModel.SUCCESS = SUCCESS;
    }

    public static int getFAILED() {
        return FAILED;
    }

    public static void setFAILED(int FAILED) {
        ResponseModel.FAILED = FAILED;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
