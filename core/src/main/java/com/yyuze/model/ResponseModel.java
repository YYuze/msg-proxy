package com.yyuze.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: yyuze
 * Created: 2018/9/14
 */
@Getter
@Setter
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
}
