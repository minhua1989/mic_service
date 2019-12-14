package com.cloud.commons.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 统计分析
 * created by lrs on 2019/8/1
 */
public interface IStatisticAnalysisDao {

    /**
     * 学生确认情况统计
     */
    List<Map<String, Object>> confirmationStatistic(@Param("roletype") String roleType, @Param("districtid") String districtid, @Param("schoolid") String schoolid, @Param("classid") String classid);

    /**
     * 学生确认情况统计(按指标)
     */
    List<Map<String, Object>> confirmationStatistic_index(@Param("roletype") String roleType, @Param("districtid") String districtid, @Param("schoolid") String schoolid, @Param("njid") String njid, @Param("classid") String classid) throws Exception;

    List<Map<String, Object>> stat_jcxkcStat2_lrs(@Param("districtid") String districtid) throws Exception;

    List<Map<String, Object>> stat_jcxkcStat2_1_lrs(@Param("districtid") String districtid, @Param("schoolid") String schoolid) throws Exception;

    List<Map<String, Object>> stat_jcxkcStat3_lrs(@Param("schoolid") String schoolid) throws Exception;

    List<Map<String, Object>> stat_analysis2_lrs(@Param("districtid") String districtid, @Param("schema") String schema) throws Exception;

    List<Map<String, Object>> stat_analysis2_1_lrs(@Param("schoolid") String schoolid, @Param("schema") String schema) throws Exception;

    List<Map<String, Object>> stat_analysis2_2_lrs(@Param("classid") String classid, @Param("schema") String schema) throws Exception;

    List<Map<String, Object>> stat_analysis3_lrs(@Param("table_name") String table_name, @Param("schoolid") String schoolid, @Param("njid") String njid, @Param("schema") String schema) throws Exception;

    List<Map<String, Object>> stat_analysis3_1_lrs(@Param("table_name") String table_name, @Param("classid") String classid, @Param("schema") String schema) throws Exception;

    List<Map<String, Object>> stat_analysis4_lrs(@Param("table_name") String table_name, @Param("schema") String schema) throws Exception;

    List<Map<String, Object>> stat_analysis4_2_lrs(@Param("table_name") String table_name, @Param("districtid") String districtid, @Param("schema") String schema) throws Exception;

    List<Map<String, Object>> stat_analysis4_1_lrs(@Param("table_name") String table_name, @Param("schoolid") String schoolid, @Param("schema") String schema) throws Exception;

    List<Map<String, Object>> stat_analysis6_lrs() throws Exception;

    List<Map<String, Object>> stat_analysis6_2_lrs(@Param("districtid") String districtid) throws Exception;

    List<Map<String, Object>> stat_analysis6_3_lrs(@Param("schoolid") String schoolid) throws Exception;

    List<Map<String, Object>> stat_analysis7_lrs(@Param("districtid") String districtid, @Param("schoolid") String schoolid) throws Exception;

    // 数据对接情况统计 (博雅网)
    List<Map<String, Object>> stat_analysis8_lrs() throws Exception;

    // 数据对接情况统计 (科艺中心)
    List<Map<String, Object>> stat_analysis9_lrs() throws Exception;

    // 数据对接指标情况统计 (博雅网)
    List<Map<String, Object>> stat_analysis8_1_lrs() throws Exception;

    // 数据对接指标情况统计 (科艺中心)
    List<Map<String, Object>> stat_analysis9_1_lrs() throws Exception;

    // 基础型课程统计
    List<Map<String, Object>> stat_analysis10_lrs(@Param("schoolid") String schoolid) throws Exception;

    // 统计情况汇总  (典型事例和基础型课程)  市级
    List<Map<String, Object>> stat_analysis12_lrs() throws Exception;

    /**
     * 统计情况汇总  (典型事例和基础型课程)  区级
     *
     * @param schooltype  学校类型: 用于区分是否特教
     * @param schooltype2 学校类型: 用于区分是否非特教
     */
    List<Map<String, Object>> stat_analysis12_1_lrs(@Param("districtid") String districtid, @Param("schooltype") String schooltype, @Param("schooltype2") String schooltype2) throws Exception;

    // 统计情况汇总  (典型事例和基础型课程)  校级
    List<Map<String, Object>> stat_analysis12_2_lrs(@Param("schoolid") String schoolid) throws Exception;

    // 统计情况汇总  (典型事例)  班级
    List<Map<String, Object>> stat_analysis12_3_lrs(@Param("classid") String classid) throws Exception;

    // 统计情况汇总  (典型事例) 学生清单  班级
    List<Map<String, Object>> stat_analysis12_4_lrs(@Param("classid") String classid, @Param("shzt") String shzt) throws Exception;

    // 统计情况汇总  (基础型课程) 学生清单  班级
    List<Map<String, Object>> stat_analysis12_5_lrs(@Param("classid") String classid, @Param("shzt") String shzt, @Param("zbid") String zbid) throws Exception;
}
