package com.cloud.commons.exceptions;

/**
 * Created by The_Answer on 2016/6/10.
 * 表名为空或不正确异常
 */
public class TableNameNullOrNotRightError extends BaseException{
    
	private static final long serialVersionUID = 1L;

	public TableNameNullOrNotRightError(){
        super(10005);
    }
}
