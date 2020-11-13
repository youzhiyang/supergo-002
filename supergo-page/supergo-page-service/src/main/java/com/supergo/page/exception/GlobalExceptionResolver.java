package com.supergo.page.exception;

import com.supergo.http.HttpResult;
import com.supergo.user.utils.HttpClient;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionResolver {

    /**
     * token认证异常
     * @param e
     * @return
     */
    @ExceptionHandler(MyAuthException.class)
    public String authExceptionResolver(MyAuthException e,HttpServletRequest request) {
        System.out.println("进入认证失败异常处理器....");
        //如果认证失败，跳转到登入页
        return "redirect:http://www.supergo-sso.com/user/loginPage";
    }

    /**
     * jwt的token过期
     */
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseBody
    public String authExceptionResolver(ExpiredJwtException e) {
        System.out.println("进入jwt的token过期异常处理器....");
        return "redirect:http://www.supergo-sso.com/user/loginPage";
    }

    /**
     * 系统异常处理
     */
    @ExceptionHandler(Exception.class)
    public HttpResult authExceptionResolver(Exception e, HttpServletResponse response, HttpServletRequest request) {
        System.out.println("进入全局异常处理器....");
        return HttpResult.error(500,e.getMessage());
    }

}
