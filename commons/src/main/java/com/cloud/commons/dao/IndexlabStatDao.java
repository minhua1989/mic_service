package com.cloud.commons.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by lrs on 2018/8/28.
 */
public interface IndexlabStatDao {

    // 学校端学生确认情况(大指标)统计
    List<Map<String, Object>> confirmBigStatSchool(Map<String, Object> params) throws Exception;

    // 学校端学生确认情况(小指标)统计
    List<Map<String, Object>> confirmSmallStatSchool(Map<String, Object> params) throws Exception;

    /**
     * 首页学生填报数统计
     *
     * @param params {districtid schooolid}
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> homeStuFillInStat(Map<String, Object> params) throws Exception;

    /**
     * 首页学生指标填报数统计
     *
     * @param params {districtid schooolid}
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> homeStuIndexlabFillInStat(Map<String, Object> params) throws Exception;

    /**
     * 学生数统计(市级)
     *
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> statStudent_shi() throws Exception;

    /**
     * 学生数统计(区级)
     *
     * @param districtid 区县id
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> statStudent_district(@Param("districtid") String districtid) throws Exception;

    /**
     * 学生数统计(校级)
     *
     * @param schoolid 学校id
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> statStudent_school(@Param("schoolid") String schoolid) throws Exception;

    /**
     * 学生数统计(班级)
     *
     * @param classid 班级id
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> statStudent_class(@Param("classid") String classid) throws Exception;

    /**
     * 根据大指标和角色id 查询小指标
     *
     * @param params
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> listSmallIndexlab(Map<String, Object> params) throws Exception;

    /**
     * 查询一级指标
     *
     * @param params
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> listSmallIndexlab2(Map<String, Object> params) throws Exception;

    /**
     * 查询各年级的确认不通过填报数
     *
     * @param tableName 填报表
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> listUnconfirmedStatistic(@Param(value = "tablename") String tableName, @Param("receiverid") String receiverid) throws Exception;

    /////


    /// 统计中心功能   仅用于项目验收的代码


    /////

    /**
     * 消息管理(市级统计)
     *
     * @param tablename
     * @param shzt
     * @return
     * @throws Exception
     */
    Map<String, Object> statFillInData_Shi(@Param(value = "tablename") String tablename,
                                           @Param(value = "shzt") String shzt) throws Exception;

    /**
     * 消息管理(区级统计)
     *
     * @param tablename
     * @param shzt
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> statFillInData_District(@Param(value = "tablename") String tablename,
                                                      @Param(value = "shzt") String shzt) throws Exception;

    /**
     * 消息管理(校级统计)
     *
     * @param tablename
     * @param shzt
     * @param districtid
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> statFillInData_School(@Param(value = "tablename") String tablename,
                                                    @Param(value = "shzt") String shzt,
                                                    @Param(value = "districtid") String districtid) throws Exception;

    /**
     * 填报记录情况统计 (市级)
     *
     * @param schema
     * @return
     */
    List<Map<String, Object>> fillInDataStat_Temp(@Param(value = "schema") String schema);

    /**
     * 填报记录情况统计 (区级)
     *
     * @param schema
     * @return
     */
    List<Map<String, Object>> fillInDataStat_District_Temp(@Param(value = "schema") String schema, @Param(value = "districtid") String districtid);

    /**
     * 填报记录情况统计 (校级)
     *
     * @param schema
     * @return
     */
    List<Map<String, Object>> fillInDataStat_School_Temp(@Param(value = "schema") String schema, @Param(value = "schoolid") String schoolid);

    /**
     * 填报记录情况统计 (年级)
     *
     * @param schema
     * @return
     */
    List<Map<String, Object>> fillInDataStat_Grade_Temp(@Param(value = "schema") String schema, @Param(value = "schoolid") String schoolid, @Param(value = "njid") String njid);

    /**
     * 毕业生归档进度统计(市级)
     *
     * @param districtid 区县id
     */
    List<Map<String, Object>> archiveProgressStat_Temp(@Param(value = "districtid") String districtid, @Param(value = "schoolid") String schoolid);

    /**
     * 毕业生归档进度统计(市级)  每日签字情况统计
     *
     * @param districtid 区县id
     */
    List<Map<String, Object>> archiveProgressStat_Daily_Temp(@Param(value = "districtid") String districtid, @Param(value = "schoolid") String schoolid
            , @Param(value = "kssj") String kssj, @Param(value = "jssj") String jssj);

    /**
     * 毕业生归档进度统计 (区级)
     *
     * @param districtid 区县id
     */
    List<Map<String, Object>> archiveProgressStat_District_Temp(@Param(value = "districtid") String districtid);

    /**
     * 毕业生归档进度统计 (校级)
     *
     * @param schoolid 学校id
     */
    List<Map<String, Object>> archiveProgressStat_School_Temp(@Param(value = "schoolid") String schoolid);

    /**
     * 指标缺失情况统计(市级)
     *
     * @param tablename 指标表名称
     * @param shzt      审核状态
     * @return
     * @throws Exception
     */
    Map<String, Object> indexlabMissStat_Shi(@Param(value = "tablename") String tablename,
                                             @Param(value = "shzt") String shzt,
                                             @Param(value = "tbformid") String tbformid) throws Exception;

    /**
     * 指标缺失情况统计(区级)
     *
     * @param tablename 指标表名称
     * @param shzt      审核状态
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> indexlabMissStat_District(@Param(value = "tablename") String tablename,
                                                        @Param(value = "shzt") String shzt,
                                                        @Param(value = "tbformid") String tbformid) throws Exception;

    /**
     * 指标缺失情况统计(校级)
     *
     * @param tablename 指标表名称
     * @param shzt      审核状态
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> indexlabMissStat_School(@Param(value = "tablename") String tablename,
                                                      @Param(value = "shzt") String shzt,
                                                      @Param(value = "tbformid") String tbformid,
                                                      @Param(value = "districtid") String districtid) throws Exception;

    /**
     * 已填报情况统计(市级)
     *
     * @param tablename 指标表名称
     * @return
     * @throws Exception
     */
    Map<String, Object> fillInStat_Shi(@Param("tablename") String tablename) throws Exception;

    /**
     * 已填报情况统计(区级)
     *
     * @param tablename 指标表名称
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> fillInStat_District(@Param("tablename") String tablename) throws Exception;

    /**
     * 已填报情况统计(校级)
     *
     * @param tablename 指标表名称
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> fillInStat_School(@Param("tablename") String tablename, @Param("districtid") String districtid, @Param("schoolid") String schoolid) throws Exception;

    /**
     * 已填报情况统计(基础型课程)
     *
     * @param districtid 区县id
     * @param schoolid   学校id
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> fillInStat_Jcxkc(@Param("districtid") String districtid, @Param("schoolid") String schoolid) throws Exception;

    /**
     * 报告归档进度监控
     *
     * @param districtid 区县id
     * @return
     */
    List<Map<String, Object>> reportArchiveStat(@Param("districtid") String districtid,
                                                @Param("schoolid") String schoolid,
                                                @Param("njid") String njid,
                                                @Param("classid") String classid) throws Exception;

    /**
     * 报告生成进度监控
     *
     * @param districtid 区县id
     * @return
     */
    List<Map<String, Object>> reportGeneratorStat(@Param("districtid") String districtid,
                                                  @Param("schoolid") String schoolid,
                                                  @Param("njid") String njid,
                                                  @Param("classid") String classid) throws Exception;

    /**
     * 短信统计
     *
     * @param senduserid 发送人id
     * @return json
     */
    List<Map<String, Object>> shortMessageStat(@Param("senduserid") String senduserid) throws Exception;

    /**
     * 学生确认情况统计(学生)
     */
    List<Map<String, Object>> countFillInByStu(@Param("schoolid") String schoolid
            , @Param("njid") String njid
            , @Param("classid") String classid
            , @Param("table_name") String table_name
            , @Param("shzt") String shzt) throws Exception;

    /**
     * 学生确认情况统计(班级)
     */
    List<Map<String, Object>> countFillInByClass(@Param("schoolid") String schoolid
            , @Param("njid") String njid
            , @Param("table_name") String table_name
            , @Param("shzt") String shzt) throws Exception;

    /**
     * 学生确认情况大指标统计(市级)
     */
    List<Map<String, Object>> confirmBigStatCity(Map<String, Object> params) throws Exception;

    /**
     * 学生确认情况大指标统计(区级)
     */
    List<Map<String, Object>> confirmBigStatDistrict(Map<String, Object> params) throws Exception;

    /**
     * 学生确认情况小指标统计(市级)
     */
    List<Map<String, Object>> confirmSmallStatCity(Map<String, Object> params) throws Exception;

    /**
     * 学生确认情况小指标统计(区级)
     */
    List<Map<String, Object>> confirmSmallStatDistrict(Map<String, Object> params) throws Exception;

    /**
     * 学生确认情况大指标统计(班级)
     */
    List<Map<String, Object>> confirmBigStatClass(Map<String, Object> params) throws Exception;

    /**
     * 学生确认情况小指标统计(班级)
     */
    List<Map<String, Object>> confirmSmallStatClass(Map<String, Object> params) throws Exception;

    /**
     * 学生确认情况大指标统计(学生)
     */
    List<Map<String, Object>> confirmBigStatStu(Map<String, Object> params) throws Exception;

    /**
     * 学生确认情况小指标统计(学生)
     */
    List<Map<String, Object>> confirmSmallStatStu(Map<String, Object> params) throws Exception;


    /**
     * 填报情况小指标统计(市级)
     */
    List<Map<String, Object>> statOne(Map<String, Object> params) throws Exception;

    /**
     * 填报情况小指标统计(区级)
     */
    List<Map<String, Object>> statTwo(Map<String, Object> params) throws Exception;

    /**
     * 填报情况小指标统计(校级)
     */
    List<Map<String, Object>> statThree(Map<String, Object> params) throws Exception;

    /**
     * 填报情况小指标统计(班级)
     */
    List<Map<String, Object>> statFour(Map<String, Object> params) throws Exception;

    /**
     * 填报情况小指标统计(学生)
     */
    List<Map<String, Object>> statFive(Map<String, Object> params) throws Exception;

    /**
     * 填报情况小指标统计(总计)
     */
    List<Map<String, Object>> statSix(Map<String, Object> params) throws Exception;

}
