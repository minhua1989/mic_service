package com.cloud.commons.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloud.commons.global.DataMap;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by The_Answer on 2016/6/14.
 * 返回报文构建工具
 */
public class ResponseUtils {

    /**
     * @param request Http请求对象
     * @return 将Request中的parameter取出来封装成Map返回
     */
    public static Map<String, Object> createRequestParamsMapNew(HttpServletRequest request) {
        Map<String, Object> rs = new DataMap<>();
        Enumeration<String> elmts = request.getParameterNames();
        while (elmts.hasMoreElements()) {
            String nextStr = elmts.nextElement();
            rs.put(nextStr.trim(), request.getParameter(nextStr));
        }
        return rs;
    }

    private static JSONObject createResponseBody(boolean isSuccess, String message, Object... datas) {
        JSONObject result = new JSONObject();
        try {
            result.put("status", isSuccess ? "success" : "error");
            result.put("message", message);
            if (datas != null && datas.length > 0) {
                if (datas.length == 1) {
//                    result.put("data", datas[0]);
                    if (datas[0] instanceof JSONObject) {
                        result.putAll((JSONObject) datas[0]);
                    } else {
                        result.put("data", datas[0]);
                    }
                } else {
                    List<Object> data = new ArrayList<>();
                    Collections.addAll(data, datas);
                    result.put("data", data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param message 需要传递的信息
     * @param datas   需要传递的数据（可以为多个）
     * @return 构建处理成功的Json对象
     */
    public static JSONObject createSuccessResponseBody(String message, Object... datas) {
        return createResponseBody(true, message, datas);
    }

    /**
     * @param datas 需要传递的数据（可以为多个）
     * @return 构建处理成功的Json对象
     */
    public static JSONObject createSuccessResponseBody(Object... datas) {
        return createSuccessResponseBody("处理成功", datas);
    }

    public static JSONObject createErrorResponseBody() {
        return createSuccessResponseBody("trace failed");
    }

    /**
     * @param message 需要传递的信息
     * @param datas   需要传递的数据（可以为多个）
     * @return 构建处理失败的Json对象
     */
    public static JSONObject createErrorResponseBody(String message, Object... datas) {
        return createResponseBody(false, message, datas);
    }


}
