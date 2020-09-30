package com.supergo.sso.service;

import com.supergo.http.HttpResult;
import com.supergo.pojo.User;
import com.supergo.service.base.BaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService extends BaseService<User> {

    HttpResult doLogin(User user, HttpServletResponse response);

    public Boolean hasKey(String key);

    public void loginOut(HttpServletRequest request, HttpServletResponse response);
}
