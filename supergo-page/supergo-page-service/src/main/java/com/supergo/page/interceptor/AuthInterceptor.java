package com.supergo.page.interceptor;

import com.supergo.page.exception.MyAuthException;
import com.supergo.page.util.Const;
import com.supergo.page.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 身份认证拦截器
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
        System.out.println("authorization:" + authorization);
        String requestURL = request.getRequestURL().toString();
        System.out.println("00000:   " + requestURL.toString());
        System.out.println("llll:   " + StringUtils.isBlank(authorization));

        //判断token是否合法
        if(StringUtils.isBlank(authorization)) {
            //判断是否是刷新页面请求，放行
            if(requestURL.contains(Const.htmlFlash) || requestURL.contains(Const.unloginAddOrderCart) ||
                    requestURL.contains(Const.getItemByGoodsId) || requestURL.contains(Const.showOrderCart)) {
                System.out.println("````````````````");
                request.setAttribute("userInfo",null);
                return true;
            }
            throw new MyAuthException("无认证信息");
        }

        System.out.println("llll:   " + !authorization.startsWith("Bearer"));
        //如果不合法，返回错误小消息
        if(!authorization.startsWith("Bearer")) {
            throw new MyAuthException("认证信息不合法");
        }

        //如果合法，执行以下处理
        final String token = authorization.substring(7);
            //判断token是否合法
        Claims claims = jwtUtil.parseToken(token);
        System.out.println("claims----:  " + claims.toString());
        //获取token值
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("loginInfo" + claims.getId());
        //如果缓存存在，自动续缓存时间
        if(!entries.isEmpty()) {
            //刷新token过期时间
            stringRedisTemplate.expire("loginInfo" + claims.getId(),10 , TimeUnit.MINUTES);
        } else {
            throw new MyAuthException("会话过期请从新登录");
        }
        request.setAttribute("userInfo",claims);

        return true;
    }
}
