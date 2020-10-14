package com.supergo.manager.interceptor;

import com.supergo.manager.exception.MyAuthException;
import com.supergo.manager.util.Const;
import com.supergo.manager.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 身份认证拦截器
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 前置处理器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获得request对象
        //从request对象中去请求头的Authorization信息，从而取token
        String authorization = request.getHeader("Authorization");
        System.out.println("authorization:   " + authorization);
        String requestURL = request.getRequestURL().toString();
        System.out.println("requestURL:   " + requestURL);

        //判断token是否合法
        if(StringUtils.isBlank(authorization)) {
            if(requestURL.contains(Const.unloginAddOrderCart)) {
                System.out.println("````````````````");
                return true;
            }
            throw new MyAuthException("无认证信息");
        }
        //如果不合法，返回错误小消息
        if(!authorization.startsWith("Bearer")) {
            throw new MyAuthException("认证信息不合法");
        }
        //如果合法，执行以下处理
        final String token = authorization.substring(7);
        //判断token是否合法
        Claims claims = jwtUtil.parseToken(token);
        System.out.println(claims.toString());

        request.setAttribute("userInfo",claims);

        return true;
    }
}
