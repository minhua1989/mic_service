package com.cloud.commons.exceptions;


/**
 * Created by The_Answer on 2016/7/12.
 */
public class BaseException extends Exception{

	private static final long serialVersionUID = 1L;

	protected String code;

    protected String msg;

    public BaseException(Integer code){
        String msg = ExceptionDict.getExceptionMessageByExceptionCode(code);
        this.msg=msg==null?this.code:msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

    @Override
    public void printStackTrace() {
        System.out.println("业务处理失败，失败原因："+this.msg);
    }
}
