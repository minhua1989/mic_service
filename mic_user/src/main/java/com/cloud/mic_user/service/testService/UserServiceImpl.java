package com.cloud.mic_user.service.testService;

import com.alibaba.fastjson.JSONObject;
import com.cloud.commons.componet.BaseDaoComponent;
import com.cloud.commons.utils.CollectionUtils;
import com.cloud.commons.utils.ResponseUtils;
import com.cloud.commons.dao.bean.*;
import com.cloud.commons.dao.bean.UpdateParams;
import com.cloud.commons.datasource.DataSourceNames;
import com.cloud.commons.datasource.annotation.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Create by The_Answer on 2018/4/4.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BaseDaoComponent baseDaoComponent;


    @Override
    @DataSource(name = DataSourceNames.FIRST)
    public JSONObject listUser(Map<String, Object> params) throws Exception{

        QueryParams listUser = QueryParams.createQueryParams("t_user");
        List<Map<String,Object>> userList = baseDaoComponent.selectDataByParams(listUser);

        return ResponseUtils.createSuccessResponseBody("查询成功",userList);
    }

    @Override
    @DataSource(name = DataSourceNames.FIRST)
    public JSONObject selectUserById(Map<String, Object> params) throws Exception {

        String userId = (String) params.get("id");
        QueryParams selectUserById = QueryParams.createQueryParams("t_user");
        selectUserById.addQueryParams(Parameter.createParameter("id",userId));
        List<Map<String,Object>> userList  = baseDaoComponent.selectDataByParams(selectUserById);

        return ResponseUtils.createSuccessResponseBody("查询成功",userList.get(0));
    }

    @Override
    @DataSource(name = DataSourceNames.FIRST)
    @Transactional(rollbackFor={Exception.class}) // 指定事物
    public JSONObject insertUser(Map<String, Object> params) throws Exception {

        InsertParams insertUser = InsertParams.createInsertParams("t_user");
        params.put("id", UUID.randomUUID().toString());
        insertUser.addParamsForMap(CollectionUtils.removeEmptyKeyByParams(params));
        baseDaoComponent.insertDataByParams(insertUser);

        if(true){
            throw new Exception("系统异常");
        }

        return ResponseUtils.createSuccessResponseBody("添加成功");
    }

    @Override
    @DataSource(name = DataSourceNames.FIRST)
    public JSONObject updateUserById(Map<String, Object> params) throws Exception {

        String userId = (String) params.remove("id");

        UpdateParams updateUserById = UpdateParams.createUpdateParams("t_user");
        updateUserById.addParamsForMap(CollectionUtils.removeEmptyKeyByParams(params));
        updateUserById.addWhereParameter(Parameter.createParameter("id",userId));
        baseDaoComponent.updateDataByParams(updateUserById);

        return ResponseUtils.createSuccessResponseBody("修改成功");
    }

    @Override
    @DataSource(name = DataSourceNames.FIRST)
    public JSONObject deleteUserById(Map<String, Object> params) throws Exception {

        String userId = (String) params.remove("id");

        DeleteParams deleteUserById = DeleteParams.createDeleteParams("t_user",Parameter.createParameter("id",userId));
        baseDaoComponent.deleteDataByParams(deleteUserById);

        return ResponseUtils.createSuccessResponseBody("删除成功");
    }

}
