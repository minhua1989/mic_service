package com.cloud.mic_user.service.testService;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Create by The_Answer on 2018/4/4.
 */
public interface UserService {

    public JSONObject listUser(Map<String, Object> params) throws Exception;

    public JSONObject selectUserById(Map<String, Object> params) throws Exception;

    public JSONObject insertUser(Map<String, Object> params) throws Exception;

    public JSONObject updateUserById(Map<String, Object> params) throws Exception;

    public JSONObject deleteUserById(Map<String, Object> params) throws Exception;

}
