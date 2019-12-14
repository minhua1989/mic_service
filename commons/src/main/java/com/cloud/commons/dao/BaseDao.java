package com.cloud.commons.dao;

import com.cloud.commons.dao.bean.IArguments;
import com.cloud.commons.dao.bean.IParams;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by The_Answer on 2016/6/10.
 * 标准DAO（通用）
 */
public interface BaseDao {

    /**
     * @param params 根据条件查询记录
     * @return 查询到的结果集
     */
    List<Map<String, Object>> selectDataByParams(IParams params) throws SQLException;

    /**
     * @param params 更新条件参数
     * @return 受影响的记录数
     */
    int updateDataByParams(IParams params) throws SQLException;

    /**
     * @param params 新增条件参数
     */
    void insertDataByParams(IParams params) throws SQLException;

    /**
     * @param params 删除条件参数
     */
    void deleteDataByParams(IParams params) throws SQLException;

    /**
     * @param params 创建表
     */
    void createTableByParams(IParams params) throws SQLException;

    /**
     * @param params 删除表
     */
    void dropTableByParams(IParams params) throws SQLException;

    /**
     * @param params 操作表字段
     */
    void alterTableByParams(IParams params) throws SQLException;

    /**
     * pageHelper分页查询
     */
    List<Map<String, Object>> selectDataByArguments(IArguments arguments) throws SQLException;
}
