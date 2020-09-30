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
        String requestURL = request.getRequestURL().toString();
        System.out.println("requestURL:   " + requestURL);

        //如果认证失败，跳转到登入页
        return "redirect:http://www.supergo-sso.com/user/loginPage";
    }

    /**
     * jwt的token过期
     */
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseBody
    public HttpResult authExceptionResolver(ExpiredJwtException e) {
        return HttpResult.error("token已经过期");
    }

    /**
     * 系统异常处理
     */
    @ExceptionHandler(Exception.class)
    public HttpResult authExceptionResolver(Exception e, HttpServletResponse response, HttpServletRequest request) {
        return HttpResult.error(500,e.getMessage());
    }

}
