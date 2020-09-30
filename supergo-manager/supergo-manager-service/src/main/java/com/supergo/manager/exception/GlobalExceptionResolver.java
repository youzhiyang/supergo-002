package com.supergo.manager.exception;

import com.supergo.http.HttpResult;
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
//@ControllerAdvice
public class GlobalExceptionResolver {

    /**
     * token认证异常
     * @param e
     * @return
     */
    @ExceptionHandler(MyAuthException.class)
    public String authExceptionResolver(MyAuthException e,HttpServletRequest request) {
        System.out.println("aaaaaaaaaa");
        StringBuffer requestURL = request.getRequestURL();
        //String baseUrl = getPathPrefix(requestURL.toString());
        //如果认证失败，跳转到登入页
        return "redirect:http://localhost:9999/api/sso/user/loginPage?url=" + requestURL;
    }

    /**
     * 获取路径前缀
     * @param url
     * @return
     */
//    public String getPathPrefix(String url) {
//        //url = "http://windows10.microdone.cn:9004/html/build/149187842868044";
//        int index = url.lastIndexOf(":");
//        String str1 = url.substring(0,index);
//        String str2 = url.substring(index);
//        String[] strArry = str2.split("/");
//        String str3 = str1 + strArry[0];
//        return str3;
//    }

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
