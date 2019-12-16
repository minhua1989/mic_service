package com.cloud.commons.dao.bean;


import com.cloud.commons.utils.BaseChecks;
import com.cloud.commons.exceptions.ParameterNumberError;
import com.cloud.commons.exceptions.TableNameNullOrNotRightError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The_Answer on 2016/6/10.
 * 删除表参数实体
 */
public class DeleteParams implements IParams {

    private String tableName;

    private List<Parameter> params;

    private static String db = "oracle";

    private DeleteParams() {
    }

    protected void addParams(Parameter... params){
        for(Parameter param:params){
            addParam(param);
        }
    }

    protected void addParam(Parameter param){
        if(params==null)params=new ArrayList<>();
        if(param!=null)params.add(param);
    }

    protected void setTableName(String tbname){
        if(BaseChecks.checkTableNameIsRight(tbname)) {
            this.tableName = tbname;
        }
    }

    public static DeleteParams createDeleteParams(String tableName, Parameter... params){
        DeleteParams dp = new DeleteParams();
        dp.setTableName(tableName);
        dp.addParams(params);
        return dp;
    }

    /**
     * 创建删除的方法
     * @param database 需要指定是什么数据库，默认为oracle
     * @param tableName
     * @param params
     * @return
     */
    public static DeleteParams createDeleteParams(String database,String tableName, Parameter... params){
        DeleteParams dp = new DeleteParams();
        dp.setTableName(tableName);
        dp.addParams(params);
        db = database;
        return dp;
    }

    @Override
    public String getExecuteSql() throws Exception{
        StringBuffer sb = new StringBuffer();
        if(!BaseChecks.checkTableNameIsRight(tableName))throw new TableNameNullOrNotRightError();
        sb.append(tableName+" "+getAlias()+" where ");
        if(params==null||params.isEmpty())throw new ParameterNumberError();
        for(int i=0,len=params.size();i<len;i++){
            if(i>0&&i<len){
                sb.append(params.get(i).isAnd()?" and ":" or ");
            }
            sb.append(params.get(i).getQuerySql());
        }
        return sb.toString();
    }

    @Override
    public boolean isCanBeExecute() {
        return BaseChecks.checkTableNameIsRight(tableName)&&params!=null&&params.size()>0;
    }

    @Override
    public void printSelf() {
        System.out.println("当前为删除操作");
        System.out.println("当前表名： "+this.tableName);
        try {
            System.out.println("当前SQL： "+getExecuteSql());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAlias() {
        if(db.equals("oracle")){
            return "";
        }
        if(db.equals("mysql")){
            return "m";
        }
        return "";
    }
}
