package com.cloud.commons.exceptions;

/**
 * Created by The_Answer on 2016/6/10.
 * 参数数量不正确
 */
public class ParameterNumberError extends BaseException {
    
	private static final long serialVersionUID = 1L;

	public ParameterNumberError() {
        super(10004);
    }
}
