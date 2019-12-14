package com.cloud.mic_user.service.testService;

import com.alibaba.fastjson.JSONObject;
import com.cloud.commons.componet.BaseDaoComponent;
import com.cloud.commons.utils.ResponseUtils;
import com.cloud.commons.dao.bean.DeleteParams;
import com.cloud.commons.dao.bean.Parameter;
import com.cloud.commons.dao.bean.QueryParams;
import com.cloud.commons.datasource.DataSourceNames;
import com.cloud.commons.datasource.DataSourceTypes;
import com.cloud.commons.datasource.annotation.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private Environment env;

    @Override
    @DataSource(name = DataSourceNames.FIRST) // 指定数据源，不指定默认第一个数据源
    public JSONObject listUser1(Map<String, Object> params) throws Exception {
        QueryParams listUser = QueryParams.createQueryParams("t_user");
        params.put("offset", "0");
        params.put("limit", "3");
        params.put("database", DataSourceTypes.ORACLE); // 指定数据库分页，ORACLE时可不写
        listUser.addQueryParamsByMap(params);
        listUser.printSelf();
        List<Map<String, Object>> userList = baseDaoComponent.selectDataByParams(listUser);
        return ResponseUtils.createSuccessResponseBody("查询成功", userList);
    }


    @Override
    @DataSource(name = DataSourceNames.FIRST) // 指定数据源，不指定默认第一个数据源
    @Transactional(rollbackFor = Exception.class) // 指定事物
    public JSONObject deleteUserById(Map<String, Object> params) throws Exception {

        String userId = (String) params.remove("id");
        DeleteParams deleteUserById = DeleteParams.createDeleteParams("t_user", Parameter.createParameter("id", userId));
        deleteUserById.printSelf();
        baseDaoComponent.deleteDataByParams(deleteUserById);

        return ResponseUtils.createSuccessResponseBody("删除成功");
    }

    @Override
    @DataSource(name = DataSourceNames.SECOND) // 指定数据源，不指定默认第一个数据源
    public JSONObject listSchool1(Map<String, Object> params) throws Exception {
        QueryParams listSchool = QueryParams.createQueryParams("t_schoolinfo");
        params.put("offset", "0");
        params.put("limit", "10");
//        params.put("database", DataSourceTypes.MYSQL);// 指定数据库分页，ORACLE时可不写
        listSchool.addQueryParamsByMap(params);
        listSchool.printSelf();
        List<Map<String, Object>> schList = baseDaoComponent.selectDataByParams(listSchool);

        return ResponseUtils.createSuccessResponseBody("查询成功", schList);
    }


    @Override
    @DataSource(name = DataSourceNames.THIRD) // 指定数据源，不指定默认第一个数据源
    public JSONObject listSchool2(Map<String, Object> params) throws Exception {

        params.put("offset", "0");
        params.put("limit", "10");
        params.put("database", DataSourceTypes.MYSQL);// 指定数据库分页，ORACLE时可不写

        QueryParams listSchool = QueryParams.createQueryParams("t_schoolinfo");
        listSchool.addQueryParamsByMap(params);
        List<Map<String, Object>> schList = baseDaoComponent.selectDataByParams(listSchool);

        return ResponseUtils.createSuccessResponseBody("查询成功", schList);
    }

}
