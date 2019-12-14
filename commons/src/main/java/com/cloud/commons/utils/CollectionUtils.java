package com.cloud.commons.utils;

import com.alibaba.fastjson.JSONArray;
import com.cloud.commons.global.DataMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by The_Answer on 2016/7/6.
 */
public class CollectionUtils {


    public static String hbMapValues(Map<String, Object> dest, String... keys) {
        StringBuffer rs = new StringBuffer();
        for (String key : keys) {
            Object value = dest.get(key);
            if (value != null) rs.append(value.toString().trim());
        }
        return rs.toString();
    }

    public static void removeMapValueByKeys(Map<String, Object> dest, String... key) {
        for (String k : key) {
            dest.remove(k);
        }
    }

    public static String[] toArray(List<String> list) {
        String[] rs = new String[list.size()];
        for (int i = 0, len = list.size(); i < len; i++) {
            rs[i] = list.get(i);
        }
        return rs;
    }

    public static void castStringToDate(String fmt, Map<String, Object> dest, String... keys) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fmt);
        for (String key : keys) {
            String curr = (String) dest.get(key);
            if (curr != null) {
                Date currDate = simpleDateFormat.parse(curr);
                dest.put(key, currDate);
            }
        }
    }

    public static Map<String, Object> copyValueByKeys(Map<String, Object> map, String... keys) {
        Map<String, Object> rs = new DataMap<>();
        for (String key : keys) {
            rs.put(key, map.get(key));
        }
        return rs;
    }

    public static Object[] getMapValueByKeys(Map<String, Object> map, String... keys) {
        Object[] rs = new Object[keys.length];
        for (int i = 0, len = keys.length; i < len; i++) {
           /* if(keys[i].equalsIgnoreCase("msg")){
                try {
                    Clob clob = new SerialClob(map.get(keys[i]).toString().toCharArray());
                    rs[i] = clob;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                continue;
            }*/
            rs[i] = map.get(keys[i]);
        }
        return rs;
    }

    /**
     * 将集合中值为NULL或者值为空字符串的数据过滤掉
     *
     * @param params 需要过滤的集合
     * @return 过滤后的集合
     */
    public static Map<String, Object> removeEmptyKeyByParams(Map<String, Object> params) {
        Map<String, Object> map = new DataMap<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().toString().isEmpty())
                map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }


    /**
     * 将传入的集合中的键，转换为小写并返回
     *
     * @param dest 需要转换的集合
     * @return 已转换的集合
     */
    public static Map<String, Object> converMapKeyToLowerCaseByMap(Map<String, Object> dest) {
        Map<String, Object> current = new DataMap<>();
        for (Map.Entry<String, Object> entry : dest.entrySet()) {
            current.put(entry.getKey(), entry.getValue());
        }
        return current;
    }

    /**
     * 将传入的集合中的键，转换为小写并返回
     *
     * @param dest 需要转换的集合
     * @return 已转换的集合
     */
    public static Map<String, Object> converMapKeyToLowerCaseByStrMap(Map<String, String> dest) {
        Map<String, Object> current = new DataMap<>();
        for (Map.Entry<String, String> entry : dest.entrySet()) {
            current.put(entry.getKey(), entry.getValue());
        }
        return current;
    }

    /**
     * 将传入的集合列表中的键，转换为小写并返回
     *
     * @param dest 需要转换的集合列表
     * @return 已转换的集合列表
     */
    public static List<Map<String, Object>> converMapKeyToLowerCase(List<Map<String, Object>> dest) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> item : dest) {
            Map<String, Object> current = new DataMap<>();
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                current.put(entry.getKey(), entry.getValue());
            }
            result.add(current);
        }
        return result;
    }

    /**
     * 将待复制的集合列表中的每一个元素复制一份并返回
     *
     * @param dest 待复制的集合列表
     * @return 列表的副本
     */
    public static List<Map<String, Object>> cloneDataList(List<Map<String, Object>> dest) {
        List<Map<String, Object>> rs = new ArrayList<>();
        for (Map<String, Object> item : dest) {
            Map<String, Object> curr = new HashMap<>();
            for (Map.Entry<String, Object> map : item.entrySet()) {
                curr.put(map.getKey(), map.getValue());
            }
            rs.add(curr);
        }
        return rs;
    }

    /**
     * @param desc 待复制的集合
     * @return 复制的副本
     */
    public static Map<String, Object> cloneMapData(Map<String, Object> desc) {
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<String, Object> m : desc.entrySet()) {
            map.put(m.getKey(), m.getValue());
        }
        return map;
    }


    /**
     * 保留主要的字段
     *
     * @param desc       数据集
     * @param keepFields 需要保留的字段
     * @return 新数据集
     */
    public static Map<String, Object> keepPrimaryField(Map<String, Object> desc, String... keepFields) {
        if (null == desc || desc.size() == 0) return null;
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<String, Object> m : desc.entrySet()) {
            String key = m.getKey();
            for (String keepField : keepFields) {
                if (key.toLowerCase().equalsIgnoreCase(keepField)) {
                    map.put(key, m.getValue());
                    break;
                }
            }
        }
        return map;
    }

    /**
     * 保留主要的非空字段
     *
     * @param desc       数据集
     * @param keepFields 需要保留的字段
     * @return 新数据集
     */
    public static Map<String, Object> keepPrimaryNotEmptyField(Map<String, Object> desc, String... keepFields) {
        if (null == desc || desc.size() == 0) return null;
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<String, Object> m : desc.entrySet()) {
            String key = m.getKey();
            for (String keepField : keepFields) {
                if (key.toLowerCase().equalsIgnoreCase(keepField) && null != m.getValue() && !"".equalsIgnoreCase(m.getValue().toString())) {
                    map.put(key, m.getValue());
                    break;
                }
            }
        }
        return map;
    }

    /**
     * 将Map类型的字符串转换为Map对象列表
     *
     * @param mapStringList Map类型的字符串列表
     * @return Map对象列表
     */
    public static List<Map<String, Object>> converMapstring2MapList(List<String> mapStringList) {
        List<Map<String, Object>> newList = new ArrayList<>();
        for (String mapStringJson : mapStringList) {
            newList.add(convertMapstring2Map(mapStringJson));
        }
        return newList;
    }

    /**
     * 将Map类型的字符串转换为Map对象
     *
     * @param mapStringJson Map类型的字符串
     * @return Map对象列表
     */
    public static Map<String, Object> convertMapstring2Map(String mapStringJson) {
        Map<String, Object> newMap = new HashMap<>();
        mapStringJson = mapStringJson.replaceAll("[{}\"]", "");
        String[] mapStringArr = mapStringJson.split(",");
        for (String mapString : mapStringArr) {
            String[] str = mapString.split("=");
            newMap.put(str[0].trim(), str[1].trim());
        }
        return newMap;
    }

    /**
     * jsonArray 转 list
     */
    public static List<Map<String, Object>> convertJSONArrayToList(JSONArray datas) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0, size = datas.size(); i < size; i++) {
            list.add(datas.getJSONObject(i));
        }
        return list;
    }
}
