package com.supergo.sso.controller;

import com.supergo.http.HttpResult;
import com.supergo.pojo.User;
import com.supergo.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//跨域处理
@Controller
public class LoginController {

    @Autowired
    private LoginService loginServiceImpl;

    /**
     * 登入操作
     * @param
     * @param response
     * @return
     */
    @PostMapping("/user/doLogin")
    @ResponseBody
    public HttpResult doLogin(@RequestBody User user, HttpServletResponse response) {
        System.out.println("doLogin。。。。");
        HttpResult httpResult = loginServiceImpl.doLogin(user,response);
        return httpResult;
    }

    /**
     * 跳转登录页面
     */
    @GetMapping("/user/loginPage")
    public String loginPage(String url) {
        System.out.println("进入登入页111。。。。:  " + url);

        return "login";
    }

    /**
     * 登入退出
     */
    @GetMapping("/user/loginOut")
    @ResponseBody
    public HttpResult loginOut(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("登入退出");
        loginServiceImpl.loginOut(request,response);
        //return "redirect:http://localhost:9999/api/sso/user/loginPage";
        return HttpResult.ok();
    }

    /**
     * 验证token是否存在
     * @param key
     * @return
     */
    @RequestMapping("/redis/hasKey/{key}")
    @ResponseBody
    public Boolean hasKey(@PathVariable("key") String key) {
        return loginServiceImpl.hasKey(key);
    }

}
