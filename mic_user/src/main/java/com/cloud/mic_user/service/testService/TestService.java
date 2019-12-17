package com.cloud.mic_user.service.testService;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Create by The_Answer on 2018/4/4.
 */
public interface TestService {

    public JSONObject listSchool1(Map<String, Object> params) throws Exception;
    public JSONObject listSchool2(Map<String, Object> params) throws Exception;
    public JSONObject listSchool3(Map<String, Object> params) throws Exception;
    public JSONObject listSchool4(Map<String, Object> params) throws Exception;
    public JSONObject listSchool5(Map<String, Object> params) throws Exception;
    public JSONObject listSchool6(Map<String, Object> params) throws Exception;
    public JSONObject listUser1(Map<String, Object> params) throws Exception;
    public JSONObject deleteUserById(Map<String, Object> params) throws Exception;

}
