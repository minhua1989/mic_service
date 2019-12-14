package com.cloud.commons.dao;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IReportDao {

    List<Map<String, Object>> selectHistoryReportStudent(@Param("xsid") String xsid) throws Exception;
}
