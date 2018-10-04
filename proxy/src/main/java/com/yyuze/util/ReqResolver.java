package com.yyuze.util;

import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: yyuze
 * Created: 2018/9/14
 */
public class ReqResolver {

    public static JSONObject resolveReq(HttpServletRequest request,String reqBody) throws Exception {
        JSONObject reqJson = new JSONObject(reqBody);
        JSONObject json = new JSONObject();
        if (reqJson.has("token")) {
            json.put("token", reqJson.get("token"));
        }
        else if(request.getParameter("token")!=null){
            json.put("token", request.getParameter("token"));
        }
        else{
            throw new Exception("token is required");
        }
        if(reqJson.has("data")){
            json.put("data",reqJson.get("data"));
        }else{
            json.put("data",reqBody);
        }
        return json;
    }

}
