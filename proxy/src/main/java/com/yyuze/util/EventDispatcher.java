package com.yyuze.util;

import com.yyuze.entity.Route;
import com.yyuze.model.MessageModel;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Author: yyuze
 * Created: 2018/9/14
 */

public class EventDispatcher extends Thread {

    private String result;

    private MessageModel message;

    public EventDispatcher(MessageModel message) {
        this.message = message;
    }

    private void dispatchEvent(MessageModel message) throws IOException {
        Route route = message.getRoute();
        String headerStr = route.getRequestHeaderStr();
        String target = route.getRequestUrl() + "?access_token=" + route.getRequestToken();
        String method = route.getRequestMethod();
        this.result = this.sendHttpRequest(target, headerStr, message.getData(), method);
    }

    private String sendHttpRequest(String target, String headerStr, String body, String method) throws IOException {
        String[] headers = null;
        if (!headerStr.equals("")) {
            headers = headerStr.split("%&&%");
        }
        HttpClient httpclient = HttpClients.createDefault();
        HttpResponse response = null;
        String result = "transmit failed";
        switch (method) {
            case "GET":
                HttpGet httpGet = new HttpGet(target);
                if (headers != null) {
                    for (String parStr : headers) {
                        String[] par = parStr.split(":");
                        String key = par[0];
                        String value = par[1];
                        httpGet.addHeader(key, value);
                    }
                }
                response = httpclient.execute(httpGet);
                result = EntityUtils.toString(response.getEntity(), "utf-8");
                httpGet.releaseConnection();
                break;
            case "POST":
                HttpPost httpPost = new HttpPost(target);
                if (headers != null) {
                    for (String parStr : headers) {
                        String[] par = parStr.split(":");
                        String key = par[0];
                        String value = par[1];
                        httpPost.addHeader(key, value);
                    }
                }
                StringEntity se = new StringEntity(body, "utf-8");
                httpPost.setEntity(se);
                response = httpclient.execute(httpPost);
                result = EntityUtils.toString(response.getEntity(), "utf-8");
                httpPost.releaseConnection();
                break;
        }
        return result;
    }

    @Override
    public void run() {
        try {
            this.dispatchEvent(this.message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public MessageModel getMessage() {
        return message;
    }

    public void setMessage(MessageModel message) {
        this.message = message;
    }
}
