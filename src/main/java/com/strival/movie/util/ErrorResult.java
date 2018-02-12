package com.strival.movie.util;

/**
 * Created by xinghai on 2015/11/8.
 */
public class ErrorResult {
    private long error_code;

    private String message;

    public long getError_code() {
        return error_code;
    }

    public ErrorResult setError_code(long error_code) {
        this.error_code = error_code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResult setMessage(String message) {
        this.message = message;
        return this;
    }
}
