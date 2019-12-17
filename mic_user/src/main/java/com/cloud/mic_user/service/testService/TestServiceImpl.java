package com.cloud.mic_user.service.testService;

import com.alibaba.fastjson.JSONObject;
import com.cloud.commons.componet.BaseDaoComponent;
import com.cloud.commons.datasource.DataSource;
import com.cloud.commons.datasource.DataSourceContextHolder;
import com.cloud.commons.datasource.DataSourceType;
import com.cloud.commons.datasource.DynamicDataSource;
import com.cloud.commons.utils.ResponseUtils;
import com.cloud.commons.dao.bean.DeleteParams;
import com.cloud.commons.dao.bean.Parameter;
import com.cloud.commons.dao.bean.QueryParams;
import com.cloud.commons.dao.mic_user.TestDao;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by The_Answer on 2018/4/4.
 */

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private BaseDaoComponent baseDaoComponent;

    @Autowired
    private TestDao testDao;

    @Autowired
    private Environment env;

    @Override
    @DataSource(DataSourceType.db2) // 指定数据源，不指定默认第一个数据源
    public JSONObject listUser1(Map<String, Object> params) throws Exception {
        QueryParams listUser = QueryParams.createQueryParams("t_user");
        params.put("offset", "0");
        params.put("limit", "3");
        params.put("database", "oracle"); // 指定数据库分页，ORACLE时可不写
        listUser.addQueryParamsByMap(params);
        listUser.printSelf();
        List<Map<String, Object>> userList = baseDaoComponent.selectDataByParams(listUser);
        return ResponseUtils.createSuccessResponseBody("查询成功", userList);
    }


    @Override
    @DataSource(DataSourceType.db2) // 指定数据源，不指定默认第一个数据源
    @Transactional(rollbackFor = Exception.class) // 指定事物
    public JSONObject deleteUserById(Map<String, Object> params) throws Exception {

        String userId = (String) params.remove("id");
        DeleteParams deleteUserById = DeleteParams.createDeleteParams("t_user", Parameter.createParameter("id", userId));
        deleteUserById.printSelf();
        baseDaoComponent.deleteDataByParams(deleteUserById);

        return ResponseUtils.createSuccessResponseBody("删除成功");
    }

    @Override
    @DataSource(DataSourceType.db1) // 指定数据源，不指定默认第一个数据源
    public JSONObject listSchool1(Map<String, Object> params) throws Exception {
        QueryParams listSchool = QueryParams.createQueryParams("t_schoolinfo");
        params.put("offset", "0");
        params.put("limit", "10");
//        params.put("database", "oracle");// 指定数据库分页，ORACLE时可不写
        listSchool.addQueryParamsByMap(params);
        listSchool.printSelf();
        List<Map<String, Object>> schList = baseDaoComponent.selectDataByParams(listSchool);

        return ResponseUtils.createSuccessResponseBody("查询成功", schList);
    }


    @Override
    @DataSource(DataSourceType.db2) // 指定数据源，不指定默认第一个数据源
    public JSONObject listSchool5(Map<String, Object> params) throws Exception {

        List<Map<String, Object>> schList = testDao.selectSchSql(params);

        return ResponseUtils.createSuccessResponseBody("查询成功", schList);
    }

    @Override
    public JSONObject listSchool6(Map<String, Object> params) throws Exception {

        List<Map<String, Object>> schList = testDao.selectSchSql(params);

        return ResponseUtils.createSuccessResponseBody("查询成功", schList);
    }

    @Override
    @DataSource(DataSourceType.db2) // 指定数据源，不指定默认第一个数据源
    public JSONObject listSchool2(Map<String, Object> params) throws Exception {

        params.put("offset", "0");
        params.put("limit", "10");
        params.put("database", "mysql");// 指定数据库分页，ORACLE时可不写

        QueryParams listSchool = QueryParams.createQueryParams("t_schoolinfo");
        listSchool.addQueryParamsByMap(params);
        listSchool.printSelf();
        List<Map<String, Object>> schList = baseDaoComponent.selectDataByParams(listSchool);

        return ResponseUtils.createSuccessResponseBody("查询成功", schList);
    }
    @Override
    @DataSource(DataSourceType.db1)
    public JSONObject listSchool3(Map<String, Object> params ) throws Exception {
        // 通过id获取到drive-class、url、username、password

        JSONObject rs = ResponseUtils.createErrorResponseBody();
        rs = listSchool1(params);

        // 配置数据源

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setJdbcUrl("jdbc:oracle:thin:@101.226.173.159:1521:zxxsybtest");
        dataSource.setUsername("xjxtuser");
        dataSource.setPassword("xjxtuser_159_!QAZ");

        // 添加一个数据源到多数据源中
        DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
        Map<Object, Object> dataSourceMap = dynamicDataSource.getDataSourceMap();
        dataSourceMap.put("db3", dataSource);
        dynamicDataSource.setTargetDataSources(dataSourceMap);

        // 切换数据源
        DataSourceContextHolder.setDataSource("db3");

        // 获取用户信息
        JSONObject rs2 = ResponseUtils.createErrorResponseBody();
        rs2 = listSchool1(params);

        // 更新id为1的用户信息

        // 使用该数据源后，删除该数据源(如果不在使用)
        DynamicDataSource instance = DynamicDataSource.getInstance();
        Map<Object, Object> dataSourceMap2 = instance.getDataSourceMap();
        dataSourceMap2.remove("db3");
        instance.setTargetDataSources(dataSourceMap2);

        return null;
    }

    @Override
    public JSONObject listSchool4(Map<String, Object> params ) throws Exception {
        //mysql
        DataSourceContextHolder.setDataSource("db2");
        JSONObject rs = ResponseUtils.createErrorResponseBody();
        rs = listSchool2(params);
        DataSourceContextHolder.clearDataSource();

        //oracle
        params=new HashMap<>();
        DataSourceContextHolder.setDataSource("db1");
        JSONObject rs3 = ResponseUtils.createErrorResponseBody();
        rs3 = listSchool1(params);
        DataSourceContextHolder.clearDataSource();
        // 配置数据源

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setJdbcUrl("jdbc:oracle:thin:@101.226.173.159:1521:zxxsybtest");
        dataSource.setUsername("xjxtuser");
        dataSource.setPassword("xjxtuser_159_!QAZ");

        // 添加一个数据源到多数据源中
        DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
        Map<Object, Object> dataSourceMap = dynamicDataSource.getDataSourceMap();
        dataSourceMap.put("db3", dataSource);
        dynamicDataSource.setTargetDataSources(dataSourceMap);

        // 切换数据源
        DataSourceContextHolder.setDataSource("db3");

        // 获取
        params=new HashMap<>();
        JSONObject rs2 = ResponseUtils.createErrorResponseBody();
        rs2 = listSchool1(params);

        // 更新id为1的用户信息

        // 使用该数据源后，删除该数据源(如果不在使用)
        DynamicDataSource instance = DynamicDataSource.getInstance();
        Map<Object, Object> dataSourceMap2 = instance.getDataSourceMap();
        dataSourceMap2.remove("db3");
        instance.setTargetDataSources(dataSourceMap2);

        return null;
    }
}
