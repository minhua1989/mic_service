package com.cloud.commons.dao;


import java.util.List;
import java.util.Map;

public interface IndexlabDao {

    List<Map<String, Object>> selectAllSql(Map<String, Object> map);

    /**
     * 查询填报方式配置
     *
     * @param params {}
     * @return map
     * @throws Exception
     */
    Map<String, Object> getFillInConfig(Map<String, Object> params) throws Exception;

    void insertTypicalCaseFillIn(Map<String, Object> params) throws Exception;
}
