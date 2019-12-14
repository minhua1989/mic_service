package com.cloud.commons.dao.bean;



import com.cloud.commons.dao.pojo.LoginInfo;
import com.cloud.commons.utils.BaseChecks;

import java.util.Map;

/**
 * Created by RenJun on 2018/07/16.
 * 表字段参数实体
 */
public class AlterParams implements IParams {

    private String tableName;

    // 业务操作类型
    private String operation;

    // 增加表字段：key为字段名  value为字段类型,如：varchar2(50)
    private Map<String, String> addColumns;

    // 重命名表字段：key为旧字段名 value为新字段名
    private Map<String, String> renameColumn;

    // 删除表字段：需要删除的字段名
    private String dropColumn;

    private AlterParams() {
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setAddColumns(Map<String, String> addColumns) {
        this.addColumns = addColumns;
    }

    public void setRenameColumn(Map<String, String> renameColumn) {
        this.renameColumn = renameColumn;
    }

    public void setDropColumn(String dropColumn) {
        this.dropColumn = dropColumn;
    }

    public static AlterParams alterTableParams(String tbName) {
        AlterParams ip = new AlterParams();
        ip.setTableName(tbName);
        return ip;
    }

    @Override
    public String getExecuteSql() throws Exception {
        if (tableName == null || tableName.isEmpty()) throw new IllegalArgumentException();
        StringBuffer sb = new StringBuffer();
        sb.append(tableName);
        sb.append(" "+operation);
        // 新增字段
        if(operation.equals("add")){
            sb.append("(");
            for (Map.Entry<String, String> entry : addColumns.entrySet()) {
                sb.append(entry.getKey()+" "+entry.getValue());
                sb.append(",");
            }
            sb.setLength(sb.length() - 1);
            sb.append(")");
        }
        // 重命名字段
        if(operation.equals("rename")){
            sb.append(" column ");
            for (Map.Entry<String, String> entry : renameColumn.entrySet()) {
                sb.append(entry.getKey()+" to "+entry.getValue());
            }
        }
        // 删除字段
        if(operation.equals("drop")){
            sb.append(" column ");
            sb.append(dropColumn);
        }
        return sb.toString();
    }

    @Override
    public boolean isCanBeExecute() {
        return BaseChecks.checkTableNameIsRight(tableName);
    }

    @Override
    public void printSelf() {
        System.out.println("当前为修改表操作");
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
