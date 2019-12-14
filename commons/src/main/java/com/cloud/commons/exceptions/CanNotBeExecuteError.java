package com.cloud.commons.exceptions;

/**
 * Created by The_Answer on 2016/6/10.
 * 不能正确生成可执行SQL语句
 */
public class CanNotBeExecuteError extends BaseException{

	private static final long serialVersionUID = 1L;

	public CanNotBeExecuteError(){
        this(10001);
    }

    public CanNotBeExecuteError(Integer code) {
        super(code);
    }
}
