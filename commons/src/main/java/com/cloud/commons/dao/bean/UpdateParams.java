package com.cloud.commons.dao.bean;


import com.cloud.commons.utils.BaseChecks;
import com.cloud.commons.utils.Format;
import com.cloud.commons.dao.pojo.LoginInfo;
import com.cloud.commons.exceptions.CanNotBeExecuteError;
import com.cloud.commons.exceptions.ParameterNumberError;
import com.cloud.commons.exceptions.TableNameNullOrNotRightError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by The_Answer on 2016/6/10.
 * 更新表参数实体
 */
public class UpdateParams implements IParams {
    private String tableName;

    private List<Parameter> params;

    private List<Parameter> wheres;

    private UpdateParams() {
    }

    public void clearParams() {
        if (params != null) params.clear();
    }

    public boolean hasSetParams() {
        return params != null && params.size() > 0;
    }

    public void addParamsForMap(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value != null && !"".equals(value.toString().trim())) {
                this.addParam(Parameter.createParameter(entry.getKey(), entry.getValue()));
            }
        }
    }

    public void addParamsForMap2(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                this.addParam(Parameter.createParameter(entry.getKey(), entry.getValue()));
            }
        }
    }

    public void addUserParams(LoginInfo loginInfo) {
        this.addParam(Parameter.createParameter("updateuserid", loginInfo.getAccountid()));
        this.addParam(Parameter.createParameter("updatetime", Format.getDateTime()));
    }

    public void addWhereParameter(Parameter param) {
        if (wheres == null) wheres = new ArrayList<>();
        if (param != null) wheres.add(param);
    }

    public void addWhereParameters(Parameter... parameters) {
        if (parameters != null && parameters.length > 0) {
            for (Parameter parameter : parameters) {
                addWhereParameter(parameter);
            }
        }
    }

    public UpdateParams addParams(List<Parameter> args) {
        for (int i = 0, len = args.size(); i < len; i++) {
            addParam(args.get(i));
        }
        return this;
    }


    public UpdateParams addParam(Parameter param) {
        if (params == null) params = new ArrayList<>();
        if (param != null) params.add(param);
        return this;
    }

    public UpdateParams addParams(Parameter... params) {
        if (params != null && params.length > 0) {
            for (Parameter parameter : params) {
                addParam(parameter);
            }
        }
        return this;
    }

    protected void setTableName(String tableName) {
        if (BaseChecks.checkTableNameIsRight(tableName)) {
            this.tableName = tableName;
        }
    }

    public static UpdateParams createUpdateParamsForMap(String tbname, Map<String, Object> params) {
        UpdateParams up = new UpdateParams();
        up.setTableName(tbname);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() == null || "".equalsIgnoreCase(entry.getValue().toString().trim())) continue;
            if ("id".equalsIgnoreCase(entry.getKey())) {
                up.addWhereParameter(Parameter.createParameter(entry.getKey(), entry.getValue()));
                continue;
            }
            up.addParams(Parameter.createParameter(entry.getKey(), entry.getValue()));
        }
        return up;
    }

    public static UpdateParams createUpdateParams(String tableName, Parameter... parameters) {
        UpdateParams up = new UpdateParams();
        up.setTableName(tableName);
        up.addParams(parameters);
        return up;
    }

    @Override
    public String getExecuteSql() throws Exception {
        if (!isCanBeExecute()) throw new CanNotBeExecuteError();
        StringBuffer sb = new StringBuffer();
        if (!BaseChecks.checkTableNameIsRight(tableName)) throw new TableNameNullOrNotRightError();
        sb.append(tableName + " set ");
        if (params == null || params.isEmpty()) throw new ParameterNumberError();
        for (Parameter param : params) {
            sb.append(param.getUpdateSql());
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append(" where ");
        for (int i = 0, len = wheres.size(); i < len; i++) {
            Parameter curr = wheres.get(i);
            if (i > 0 && i < len) {
                sb.append(wheres.get(i).isAnd() ? " and " : " or ");
            }
            if (curr.getSpeator() == 1) sb.append("(");
            sb.append(wheres.get(i).getQuerySql());
            if (curr.getSpeator() == 2) sb.append(")");
        }
        return sb.toString();
    }

    @Override
    public boolean isCanBeExecute() {
        return BaseChecks.checkTableNameIsRight(tableName) && params != null && params.size() > 0 && wheres != null && wheres.size() > 0;
    }

    @Override
    public void printSelf() {
        System.out.println("当前为更新操作");
        System.out.println("当前表名： " + this.tableName);
        try {
            System.out.println("当前SQL： " + getExecuteSql());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAlias() {
        return "";
    }
}
