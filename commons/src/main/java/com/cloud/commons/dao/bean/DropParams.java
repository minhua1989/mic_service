package com.cloud.commons.dao.bean;


import com.cloud.commons.utils.BaseChecks;
import com.cloud.commons.dao.pojo.LoginInfo;

/**
 * Created by RenJun on 2018/07/16.
 * 删除表参数实体
 */
public class DropParams implements IParams {

    private String tableName;

    private DropParams() {
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public static DropParams dropTableParams(String tbName) {
        DropParams ip = new DropParams();
        ip.setTableName(tbName);
        return ip;
    }

    @Override
    public String getExecuteSql() throws Exception {
        if (tableName == null || tableName.isEmpty()) throw new IllegalArgumentException();
        StringBuffer sb = new StringBuffer();
        sb.append(tableName);
        return sb.toString();
    }

    @Override
    public boolean isCanBeExecute() {
        return BaseChecks.checkTableNameIsRight(tableName);
    }

    @Override
    public void printSelf() {
        System.out.println("当前为删除表操作");
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
