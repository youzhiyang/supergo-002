package com.supergo.sso.service.impl;

import com.supergo.http.HttpResult;
import com.supergo.mapper.UserMapper;
import com.supergo.page.feign.ApiPageFeign;
import com.supergo.pojo.User;
import com.supergo.service.base.impl.BaseServiceImpl;
import com.supergo.sso.service.LoginService;
import com.supergo.sso.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service("loginServiceImpl")
public class LoginServiceImpl extends BaseServiceImpl<User> implements LoginService {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserMapper userMapper;

    @Override
    public HttpResult doLogin(User user, HttpServletResponse response) {

        //根据用户名和密码查询用户信息
        List<User> list = userMapper.getUserByUserName(user);
        //如果没有结果、用户不存在
        if(list == null || list.size() == 0) {
            return HttpResult.error(400,"用户名或密码错误");
        }
        User user1 = list.get(0);
        System.out.print("111:   " + user.getPassword());
        System.out.print("222:   " + user1.getPassword());
        String passsword = user1.getPassword();
        //如果密码不正确返回失败
        if (!BCrypt.checkpw(user.getPassword(), user1.getPassword())) {
            return HttpResult.error(400, "用户名或密码错误");
        }
        String token = null;
        //如果用户已经登入，根据key取redis取token
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("loginInfo" + user1.getId());
        System.out.println("entries   :  " + entries.toString());
        //如果redis中已经存在token
        if(!entries.isEmpty()) {
            String redisToken = (String) entries.get(user1.getId());
            if(redisToken != null) {
                token = redisToken;
                //刷新缓存时间
                stringRedisTemplate.expire("loginInfo" + user1.getId(),10, TimeUnit.MINUTES);
            }
        }

        //登入成功，生成token
        token = jwtUtil.createToken(user1.getId() + "", user1.getUsername(), user1.getRoleId());
        System.out.println("token:  " + token);
        //保存用户信息到redis中
        stringRedisTemplate.opsForHash().put("loginInfo" + user1.getId(),user1.getId()+"",token);
        //设置过期时间
        stringRedisTemplate.expire("loginInfo" + user1.getId(),10, TimeUnit.MINUTES);

        //登入成功
        //将token保存至cookie中
        Cookie cookie = new Cookie("accessToken", token);
        response.addCookie(cookie);
        //设置超时时间
        cookie.setMaxAge(60 * 3);

        //封装httpResult数据
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(200);
        httpResult.setData(token);

        return httpResult;
    }

    /**
     * 登入退出
     * @param request
     * @param response
     */
    public void loginOut(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        //1、清楚cookie
        Cookie[] cookies = request.getCookies();

//        if(cookies != null) {
//            for(Cookie cookie : cookies) {
//                System.out.println("cookie:   " + cookie.toString());
//                if(cookie.getName().equals("accessToken")) {
//                    System.out.println("userId:-------------   " + userId);
//                    //2、清除redis
//                    stringRedisTemplate.delete("loginInfo" + userId);
//                }
//                cookie.setMaxAge(0);
//                cookie.setPath("/");
//                response.addCookie(cookie);
//            }
//        }
    }

    /**
     * 判断key是否存在
     */
    public Boolean hasKey(String key) {
        try {
            return stringRedisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
