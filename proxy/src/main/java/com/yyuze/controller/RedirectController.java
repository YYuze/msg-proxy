package com.yyuze.controller;

import com.yyuze.model.ResponseModel;
import com.yyuze.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: yyuze
 * Created: 2018/9/17
 */
@RestController
public class RedirectController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ResponseModel responseModel;


    @RequestMapping("")
    @ResponseBody
    public String sendRedirectToIndex(HttpServletResponse response){
        try {
            response.sendRedirect("/jar_manager.html");
            this.responseModel = new ResponseModel(ResponseModel.SUCCESS,"");
        } catch (IOException e) {
            this.responseModel = new ResponseModel(ResponseModel.FAILED,e.getMessage());
            this.logger.error(e.getMessage());
        }
        return JsonUtil.toJson(this.responseModel);
    }

    @RequestMapping("/redirect")
    @ResponseBody
    public String sendRedirectToRoutes(HttpServletRequest request,HttpServletResponse response){
        String target = request.getParameter("target");
        try {
            response.sendRedirect("/"+target+"_manager.html");
            this.responseModel = new ResponseModel(ResponseModel.SUCCESS,"");
        } catch (IOException e) {
            this.responseModel = new ResponseModel(ResponseModel.FAILED,e.getMessage());
            this.logger.error(e.getMessage());
        }
        return JsonUtil.toJson(this.responseModel);
    }

}
