package com.cloud.mic_user.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.commons.utils.ResponseUtils;
import com.cloud.mic_user.service.testService.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Create by The_Answer on 2018/4/4.
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping(value = "/listSchool1")
    public JSONObject listSchool1(HttpServletRequest request){

        JSONObject rs = ResponseUtils.createErrorResponseBody();
        Map<String, Object> params = ResponseUtils.createRequestParamsMapNew(request);
        try{
            rs = testService.listSchool1(params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    @GetMapping(value = "/listSchool2")
    public JSONObject listSchool2(HttpServletRequest request){

        JSONObject rs = ResponseUtils.createErrorResponseBody();
        Map<String, Object> params = ResponseUtils.createRequestParamsMapNew(request);
        try{
            rs = testService.listSchool2(params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    @GetMapping(value = "/listSchool3")
    public JSONObject listSchool3(HttpServletRequest request){

        JSONObject rs = ResponseUtils.createErrorResponseBody();
        Map<String, Object> params = ResponseUtils.createRequestParamsMapNew(request);
        try{
            rs = testService.listSchool3(params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    @GetMapping(value = "/listSchool4")
    public JSONObject listSchool4(HttpServletRequest request){

        JSONObject rs = ResponseUtils.createErrorResponseBody();
        Map<String, Object> params = ResponseUtils.createRequestParamsMapNew(request);
        try{
            rs = testService.listSchool4(params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
    @GetMapping(value = "/listUser1")
    public JSONObject listUser1(HttpServletRequest request){

        JSONObject rs = ResponseUtils.createErrorResponseBody();
        Map<String, Object> params = ResponseUtils.createRequestParamsMapNew(request);
        try{
            rs = testService.listUser1(params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    @GetMapping(value = "/deleteUserById")
    public JSONObject deleteUserById(HttpServletRequest request){

        JSONObject rs = ResponseUtils.createErrorResponseBody();
        Map<String, Object> params = ResponseUtils.createRequestParamsMapNew(request);
        try{
            rs = testService.deleteUserById(params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

}
