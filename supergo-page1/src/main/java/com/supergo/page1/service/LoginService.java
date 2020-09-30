package com.supergo.page1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.io.IOException;

@Service
public class LoginService {

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 生成login登入页面
     * @return
     * @throws IOException
     * @param userInfo
     */
//    public HttpResult buildLoginPage(UserInfo userInfo) throws IOException {
//        FileWriter fileWriter = new
//                FileWriter("D:\\用户目录\\下载\\supergo-002\\supergo-page\\src\\main\\resources\\goods\\");
//        Context context = getLoginData(userInfo);
//        templateEngine.process("login/login", context, fileWriter);
//        return HttpResult.ok();
//    }

    /**
     * 获取登入数据
     * @return
     * @param userInfo
     */
//    private Context getLoginData(UserInfo userInfo) {
//        Context context = new Context();
//
//        return context;
//    }

    /**
     * 对请求url进行解析,获取跳转路径
     * @return
     */
//    public String parseUrl(String url) {
//        //http://localhost:9999/api/page/149187842868047.html
//        String[] split = url.split("\\.");
//        String preUrl = split[0];
//        int pageIndex = preUrl.indexOf("page");
//        String jumpUrl = preUrl.substring(pageIndex + 5);
//        return jumpUrl;
//    }
}
