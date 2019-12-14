package com.cloud.commons.dao.bean;

/**
 * created by lrs on 2019/9/5
 */
public interface IArguments extends IParams {

    @Override
    String getExecuteSql() throws Exception;

    @Override
    boolean isCanBeExecute();

    @Override
    void printSelf();

    @Override
    String getAlias();

    int getLimit();

    int getOffset();

    boolean isHasPage();

    boolean isHasOrder();

    String getOrderFunction();
}
