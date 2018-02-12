package com.strival.movie.exception;

/**
 * Created by shuiyu on 2015/10/19.
 */
public class BizException extends Exception{
    /**用户名重复*/
    public final static Integer ERROR_CODE_USERNAME_EXISTED=new Integer(2000);
    /**用户不存在*/
    public final static Integer ERROR_CODE_USER_NOT_FOUND=new Integer(2001);
    /**用户密码错误*/
    public final static Integer ERROR_CODE_PASSWORD_ERROR=new Integer(2002);
    /**权限不对*/
    public final static Integer ERROR_CODE_AUTH_NOT_CORRECT = new Integer(2003);
    /**数据库实例未找到*/
    public final static Integer ERROR_CODE_INSTANCE_NOT_FOUND = new Integer(2004);
    private Integer errorCode;

    public BizException(Integer errorCode,String msg){
        super(msg);
        this.errorCode=errorCode;
    }

    public BizException(Integer errorCode,String msg,Throwable e){
        super(msg,e);
        this.errorCode=errorCode;
    }

    public Integer getErrorCode(){
        return this.errorCode;
    }
}
