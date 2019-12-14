package com.cloud.commons.dao.bean;

/**
 * Created by The_Answer on 2016/6/10.
 *  Sql参数接口
 */
public interface IParams {

    /**
     * 需要执行的SQL语句
     */
    String getExecuteSql()throws Exception;

    /**
     * 可以被执行返回True不可执行返回Fasle
     */
    boolean isCanBeExecute();

    /**
     * 在控制台打印信息
     */
    void printSelf();

    /**
     * 别名
     * @return
     */
    String getAlias();

}
