package com.cloud.commons.dao.bean;


import com.cloud.commons.utils.BaseChecks;
import com.cloud.commons.dao.pojo.LoginInfo;

import java.util.Map;

/**
 * Created by RenJun on 2018/07/16.
 * 创建表参数实体
 */
public class CreateParams implements IParams {

    private String tableName;
    private Map<String, String> columns;

    private CreateParams() {
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setColumns(Map<String, String> columns) {
        this.columns = columns;
    }

    public static CreateParams createTableParams(String tbName) {
        CreateParams ip = new CreateParams();
        ip.setTableName(tbName);
        return ip;
    }

    @Override
    public String getExecuteSql() throws Exception {
        if (tableName == null || tableName.isEmpty()) throw new IllegalArgumentException();
        StringBuffer sb = new StringBuffer();
        sb.append(tableName);
        sb.append("(");
        for (Map.Entry<String, String> entry : columns.entrySet()) {
            sb.append(entry.getKey()+" "+entry.getValue());
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean isCanBeExecute() {
        return BaseChecks.checkTableNameIsRight(tableName);
    }

    @Override
    public void printSelf() {
        System.out.println("当前为创建表操作");
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
