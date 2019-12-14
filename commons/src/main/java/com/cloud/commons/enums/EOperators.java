package com.cloud.commons.enums;

/**
 * Created by The_Answer on 2016/6/10.
 * 运算符枚举
 */
public enum EOperators {
    等于("="),
    不等于("!="),
    大于(">"),
    大于等于(">="),
    小于("<"),
    小于等于("<="),
    包含("in"),
    不包含("not in"),
    类似("like"),
    不类似("not like"),
    为空(" is null "),
    不为空("is not null"),

    增加表字段("add"),
    修改表字段("modify"),
    删除表字段("drop"),
    重命名表字段("rename");


    EOperators(String value) {
        this.value = value;
    }

    private String value;

    @Override
    public String toString() {
        return this.value;
    }
}
