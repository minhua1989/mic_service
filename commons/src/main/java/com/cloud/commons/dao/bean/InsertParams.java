package com.cloud.commons.dao.bean;


import com.cloud.commons.utils.BaseChecks;
import com.cloud.commons.utils.Format;
import com.cloud.commons.dao.pojo.LoginInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by The_Answer on 2016/6/10.
 * 新增记录参数实体
 */
public class InsertParams implements IParams {

    private String tableName;
    private List<String> columns;
    private List<DataValue> values;

    private InsertParams() {
    }

    public void addParamsForMap(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            addColumn(entry.getKey());
            addValue(DataValue.createByObject(entry.getValue()));
        }
    }

    public boolean checkColumnsValuesRight() {
        if (columns == null || values == null) return false;
        return columns.size() == values.size();
    }

    protected void addColumn(String col) {
        if (columns == null) columns = new ArrayList<>();
        if (col != null && !col.isEmpty()) {
            columns.add(col);
        }
    }

    protected void addValues(DataValue... dvs) {
        if (dvs != null && dvs.length > 0) {
            for (int i = 0, len = dvs.length; i < len; i++) {
                addValue(dvs[i]);
            }
        }
    }

    protected void addValue(DataValue dv) {
        if (this.values == null) this.values = new ArrayList<>();
        values.add(dv);
    }

    public void setValues(Object... vals) {
        if (vals != null) {
            for (Object obj : vals) {
                if (obj instanceof DataValue) {
                    addValues((DataValue) obj);
                } else {
                    addValues(DataValue.createByObject(obj));
                }
            }
        }
    }

    public void resetValues(Object... vals) {
        if (values == null) values = new ArrayList<>();
        values.clear();
        setValues(vals);
    }


    protected void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public static InsertParams createInsertParams(String tbName, Map<String, Object> params) {
        InsertParams ip = new InsertParams();
        ip.setTableName(tbName);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            ip.addColumn(entry.getKey());
            ip.setValues(entry.getValue());
        }
        return ip;
    }

    public static InsertParams createInsertParams(String tableName, String... cols) {
        if (tableName == null || cols == null || tableName.isEmpty() || BaseChecks.hasEmptyStr(cols))
            throw new IllegalArgumentException();
        InsertParams ip = new InsertParams();
        ip.setTableName(tableName);
        if (cols != null && cols.length > 0) {
            for (int i = 0, len = cols.length; i < len; i++) {
                ip.addColumn(cols[i]);
            }
        }
        return ip;
    }

    public InsertParams addUsertParams(LoginInfo loginInfo) {
        addColumn("id");
        addValue(DataValue.createByObject(UUID.randomUUID().toString()));
        addColumn("adduserid");
        addValue(DataValue.createByObject(loginInfo.getAccountid()));
        addColumn("addtime");
        addValue(DataValue.createByObject(Format.getDateTime()));
        addColumn("deleted");
        addValue(DataValue.createByObject("0"));
        return this;
    }

    @Override
    public String getExecuteSql() throws Exception {
        if (tableName == null || tableName.isEmpty()) throw new IllegalArgumentException();
        StringBuffer sb = new StringBuffer();
        sb.append(tableName);
        sb.append(" (");
        for (int i = 0, len = columns.size(); i < len; i++) {
            sb.append(columns.get(i));
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append(") values (");
        for (int i = 0, len = values.size(); i < len; i++) {
            if (values.get(i) == null) {
                sb.append("null");
            } else {
                sb.append(values.get(i).getExecuteSql());
            }
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean isCanBeExecute() {
        return BaseChecks.checkTableNameIsRight(tableName) && checkColumnsValuesRight();
    }

    @Override
    public void printSelf() {
        System.out.println("当前为新增操作");
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
