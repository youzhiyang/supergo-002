package com.supergo.manager.exception;

import org.springframework.stereotype.Component;

/**
 * 自定义登入异常类
 */
@Component
public class MyAuthException extends java.lang.Exception {

    public MyAuthException() {
    }

    public MyAuthException(String message) {
        super(message);
    }
}
