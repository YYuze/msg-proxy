package com.yyuze.controller;

import com.yyuze.manager.TransmitManager;
import com.yyuze.model.ResponseModel;
import com.yyuze.util.JsonUtil;
import com.yyuze.util.ReqResolver;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: yyuze
 * Created: 2018/8/31
 */
@RestController
@RequestMapping("/proxy")
public class ProxyController {

    @Autowired
    private TransmitManager transmitManager;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ResponseModel responseModel;

    /**
     * 接收数据的格式为
     * {
     * "type":"x",
     * "token":"xxxxxx",
     * "data":{}
     * }
     * <p>
     * 或者
     * type与token以url形式传入,如/proxy/transmit?type=xxx&token=xxxxxxxx
     * 请求body为data
     */
    @RequestMapping("/transmit")
    @ResponseBody
    public String transmit(HttpServletRequest request, @RequestBody String body) {
        this.logger.info("receiving：" + body);
        try {
            JSONObject json = ReqResolver.resolveReq(request, body);
            this.transmitManager.addToMessageQueue(json);
            this.responseModel = new ResponseModel(ResponseModel.SUCCESS, "");
        } catch (Exception e) {
            this.responseModel = new ResponseModel(ResponseModel.FAILED, e.getMessage());
            this.logger.error(e.getMessage());
        }
        return JsonUtil.toJson(this.responseModel);
    }

    @RequestMapping("/reload")
    @ResponseBody
    public String reload() {
        if(!this.transmitManager.load()){
            this.responseModel = new ResponseModel(ResponseModel.FAILED,"reloading error");
            this.logger.info("reloading error");
        }else{
            this.responseModel = new ResponseModel(ResponseModel.SUCCESS, "reloading ok");
            this.logger.info("reloading ok");
        }
        return JsonUtil.toJson(this.responseModel);
    }

    @RequestMapping("/test_rcv")
    @ResponseBody
    public String testReceive(@RequestBody String body) {
        System.out.println("test 1 received: "+body);
        return JsonUtil.toJson(new ResponseModel(ResponseModel.SUCCESS,""));
    }

    @RequestMapping("/test_rcv2")
    @ResponseBody
    public String testReceive2(@RequestBody String body) {
        System.out.println("test 2 received: "+body);
        return JsonUtil.toJson(new ResponseModel(ResponseModel.SUCCESS,""));
    }
}