package com.snowman.mymall.common;

import lombok.Data;

/**
 * @Description
 * @Author Snowman2014
 * @Date 2019/10/8 15:06
 * @Version 1.0
 **/
@Data
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = -6556202366007238729L;

    private String errmsg;
    private int errno = 500;

    public ApiException(String errmsg) {
        super(errmsg);
        this.errmsg = errmsg;
    }

    public ApiException(String errmsg, Throwable e) {
        super(errmsg, e);
        this.errmsg = errmsg;
    }

    public ApiException(String errmsg, int errno) {
        super(errmsg);
        this.errmsg = errmsg;
        this.errno = errno;
    }

    public ApiException(String errmsg, int errno, Throwable e) {
        super(errmsg, e);
        this.errmsg = errmsg;
        this.errno = errno;
    }


}
