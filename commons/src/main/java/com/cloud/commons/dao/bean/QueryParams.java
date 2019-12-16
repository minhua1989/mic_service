package com.cloud.commons.dao.bean;


import com.cloud.commons.datasource.DataSourceTypes;
import com.cloud.commons.utils.BaseChecks;
import com.cloud.commons.dao.pojo.LoginInfo;
import com.cloud.commons.exceptions.CanNotBeExecuteError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by The_Answer on 2016/6/10.
 * 查询条件对象
 */
public class QueryParams implements IParams {

    public static final int D_SCHOOL = 0;
    public static final int D_AREA = 1;
    public static final int D_CLASS = 2;
    public static final int D_STUDENT = 3;

    private boolean hasPage = false;

    private int currentMod = -1;

    private int limit = 10;

    private int offset = 0;

    private String dababase = DataSourceTypes.ORACLE;

    private List<Parameter> queryParams;

    private String tableName;

    private List<String> columns;

    private List<String> orderColumns;

    private boolean hasOrderFunction = false;

    public boolean isHasOrderFunction() {
        return hasOrderFunction;
    }


    private QueryParams() {
    }

    public void printSelfByPage() {
        try {
            System.out.println(this.getExcuteSqlByPage());
        } catch (CanNotBeExecuteError canNotBeExecuteError) {
            canNotBeExecuteError.printStackTrace();
        }
    }

    private void clearParams() {
        if (queryParams != null) queryParams.clear();
    }

    private String getExcuteSqlByPage() throws CanNotBeExecuteError {
        String rootSql = getExecuteSqlNotPage();
        StringBuffer sb = new StringBuffer();
        // oracle
        if(dababase.equals(DataSourceTypes.ORACLE)){
            sb.append(" * FROM (SELECT (SELECT COUNT(1) FROM (");
            sb.append("SELECT " + rootSql);
            sb.append(")) TOTAL , ROWNUM rn , PA.* FROM (");
            sb.append("SELECT " + rootSql);
            sb.append(") PA )");
            sb.append("WHERE rn > ");
            sb.append(offset);
            sb.append(" AND rn <= ");
            sb.append(offset + limit);
        }
//        if (dababase.equals(DataSourceTypes.ORACLE)) {
//            sb.append(" * FROM (SELECT (SELECT COUNT(1) FROM (");
//            sb.append("SELECT " + rootSql);
//            sb.append(")) TOTAL , ROWNUM rn , PA.* FROM (");
//            String rownum = "";
//            int maxSize = offset + limit;
//            // 处理有where的情况
//            if (rootSql.toLowerCase().contains("where")) {
//                rownum = " rownum <= " + maxSize;
//                rootSql = rootSql.toLowerCase().replace("where", " where " + rownum + " and ");
//            } else {
//                rownum = " where rownum <= " + maxSize;
//                // 处理有group by的情况
//                if (rootSql.toLowerCase().contains("group")) {
//                    rootSql = rootSql.toLowerCase().replace("group", rownum + " group ");
//                } else if (rootSql.toLowerCase().contains("order")) {
//                    // 处理有order的情况
//                    rootSql = rootSql.toLowerCase().replace("order", rownum + " order ");
//                }else{
//                    rootSql = rootSql.toLowerCase() + rownum;
//                }
//            }
//            sb.append("SELECT " + rootSql);
//            sb.append(") PA )");
//            sb.append("WHERE rn > ");
//            sb.append(offset);
//        }
        // mysql
        if (dababase.equals(DataSourceTypes.MYSQL)) {
            sb.append(" * FROM (SELECT (SELECT COUNT(1) FROM (");
            sb.append("SELECT " + rootSql);
            sb.append(")a ) TOTAL  , PA.* FROM (");
            sb.append("SELECT " + rootSql);
            sb.append(") PA ) b ");
            sb.append("limit ");
            sb.append(offset);
            sb.append(",");
            sb.append(limit);
        }
        return sb.toString();
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
        this.hasPage = true;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
        this.hasPage = true;
    }

    public String getDababase() {
        return dababase;
    }

    public void setDababase(String dababase) {
        this.dababase = dababase;
    }

    private Map<String, List<Map<String, Object>>> dataAreas = new HashMap<>();

    public QueryParams addQueryParamsByMap(Map<String, Object> paramsMap) {
        String sort = null;
        String order = "";
        String offset = "0";
        String limit = "10";
        String dababase = DataSourceTypes.ORACLE;
        for (Map.Entry<String, Object> item : paramsMap.entrySet()) {
            if (item.getKey().equalsIgnoreCase("sort") && item.getValue() != null) {
                sort = (String) item.getValue();
                continue;
            }
            if (item.getKey().equalsIgnoreCase("order") && item.getValue() != null) {
                order = (String) item.getValue();
                continue;
            }
            if (item.getKey().equalsIgnoreCase("offset") && item.getValue() != null) {
                offset = item.getValue().toString();
                this.hasPage = true;
                continue;
            }
            if (item.getKey().equalsIgnoreCase("limit") && item.getValue() != null) {
                limit = item.getValue().toString();
                this.hasPage = true;
                continue;
            }
            if (item.getKey().equalsIgnoreCase("database") && item.getValue() != null) {
                dababase = item.getValue().toString();
//                this.hasPage = true;
                continue;
            }
            if (item.getValue() != null) this.addQueryParams(Parameter.createParameter(item.getKey(), item.getValue()));
        }
        if (sort != null) {
            this.addOrderColumns(sort + " " + order);
        }
        if (hasPage) {
            this.setOffset(Integer.valueOf(offset));
            this.setLimit(Integer.valueOf(limit));
            this.setDababase(dababase);
        }
        return this;
    }


    public QueryParams addOrderColumns(String order) {
        if (order != null && !order.isEmpty()) {
            if (orderColumns == null) orderColumns = new ArrayList<>();
            orderColumns.add(order);
        }
        return this;
    }


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void addQueryParams(Parameter... params) {
        if (params != null && params.length > 0) {
            for (int i = 0, len = params.length; i < len; i++) {
                if (queryParams == null) queryParams = new ArrayList<>();
                queryParams.add(params[i]);
            }
        }
    }

    public void addColumns(String... cols) {
        if (cols != null && cols.length > 0) for (int i = 0, len = cols.length; i < len; i++) {
            if (columns == null) {
                columns = new ArrayList<>();
            }
            columns.add(cols[i]);
        }
    }

    public static QueryParams createQueryParams(String tbname) {
        if (tbname == null || tbname.isEmpty() || ",".contains(tbname))
            throw new IllegalArgumentException("查询表名不能为空且只能为单表");
        QueryParams queryParams = new QueryParams();
        queryParams.setTableName(tbname);
        return queryParams;
    }

    private String getExecuteSqlNotPage() throws CanNotBeExecuteError {
        if (!isCanBeExecute()) throw new CanNotBeExecuteError();
        StringBuffer sb = new StringBuffer();
        if (columns != null && columns.size() > 0) {
            for (int i = 0, len = columns.size(); i < len; i++) {
                sb.append("m." + columns.get(i));
                sb.append(",");
            }
            sb.setLength(sb.length() - 1);
        } else {
            sb.append("m.*");
        }
        sb.append(" from ");
        sb.append(tableName + " m ");
        int x = 0;
        List<String> wheres = new ArrayList<>();
        for (Map.Entry<String, List<Map<String, Object>>> map : this.dataAreas.entrySet()) {
            String tab = map.getKey().split(",")[0];
            if ("vda_systemDriver".equalsIgnoreCase(tab)) {
                if (map.getValue() == null || map.getValue().size() == 0) continue;
                String convalues = (String) map.getValue().get(0).get("convals");
                switch (currentMod) {
                    default:
                        break;
                    case D_SCHOOL:
                        sb.append(" left join (select id from (select d_control.id from t_schoolinfo d_control left join t_xd_system d_core on d_control.schoolsystem = d_core.systemcode where d_core.xdid in ( " + convalues + " ) @tj@ ) group by id ) d_key on m.schoolid = d_key.id ");
                        wheres.add("m.schoolid = d_key.id");
                        break;
                    case D_AREA:
                        sb.append(" left join (select id from (select d_control.id from t_schoolinfo d_control left join t_xd_system d_core on d_control.schoolsystem = d_core.systemcode where d_core.xdid in ( " + convalues + " ) @tj@ ) group by id ) d_key on m.schoolid = d_key.id ");
                        wheres.add("m.schoolid = d_key.id");
                        break;
                    case D_CLASS:
                        sb.append(" left join (select id,njid from (select d_control.id,d_core.njid from t_schoolinfo d_control left join vda_systemDriver d_core on d_control.schoolsystem = d_core.systemcode where d_core.xdid in (" + convalues + " ) @tj@ ) group by id,njid ) d_key on m.schoolid = d_key.id and m.njid = d_key.njid ");
                        wheres.add("m.schoolid = d_key.id and m.njid = d_key.njid");
                        break;
                }
                continue;
            }
            if ("vda_specal".equals(tab)) {
                String tmp = sb.toString();
                String convalues = (String) map.getValue().get(0).get("convals");
                tmp = tmp.replaceAll("@tj@", "or d_control.schooltype in (" + convalues + ") ");
                sb = new StringBuffer(tmp);
                continue;
            }

            String fk = map.getKey().split(",")[1];
            sb.append(" left join " + tab + " s" + x + " on m." + fk + " = s" + x + ".id");
            for (Map<String, Object> cvs : map.getValue()) {
                String concol = (String) cvs.get("concol");
                String convals = (String) cvs.get("convals");
                wheres.add("s" + x + "." + concol + " in (" + convals + ") ");
            }
            x++;
        }
        String tmp = sb.toString();
        tmp = tmp.replaceAll("@tj@", "");
        sb = new StringBuffer(tmp);
        boolean hasWhere = false;
        if (queryParams != null && queryParams.size() > 0) {
            sb.append(" where ");
            hasWhere = true;
            for (int i = 0, len = queryParams.size(); i < len; i++) {
                Parameter curr = queryParams.get(i);
                if (i > 0 && i < len) {
                    sb.append(queryParams.get(i).isAnd() ? " and " : " or ");
                }
                if (curr.getSpeator() == 1) sb.append("(");
                if (curr.getDoubleSpeator() == 1) sb.append("((");
                sb.append("m." + queryParams.get(i).getQuerySql());
                if (curr.getSpeator() == 2) sb.append(")");
                if (curr.getDoubleSpeator() == 2) sb.append("))");
            }
        }
        if (wheres.size() > 0) {
            if (!hasWhere) {
                sb.append(" where ");
            } else {
                sb.append(" and ");
            }
            for (int i = 0, len = wheres.size(); i < len; i++) {
                if (i > 0 && i < len) {
                    sb.append(" and ");
                }
                sb.append(wheres.get(i));
            }
        }
        if (orderColumns != null && orderColumns.size() > 0) {
            sb.append(" order by ");
            for (int i = 0, len = orderColumns.size(); i < len; i++) {
                if (i > 0 && i < len) sb.append(", ");
                if (isHasOrderFunction()) {
                    sb.append(orderColumns.get(i) + " ");
                } else {
                    sb.append("m." + orderColumns.get(i) + " ");
                }
            }
        }
        return sb.toString();
    }


    @Override
    public String getExecuteSql() throws CanNotBeExecuteError {
        return hasPage ? getExcuteSqlByPage() : getExecuteSqlNotPage();
    }

    @Override
    public boolean isCanBeExecute() {
        return BaseChecks.checkTableNameIsRight(tableName);
    }

    @Override
    public void printSelf() {
        System.out.println("当前为查询操作");
        System.out.println("当前表名： " + this.tableName);
        try {
            System.out.println("当前SQL： " + this.getExecuteSql());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAlias() {
        return "";
    }
}
