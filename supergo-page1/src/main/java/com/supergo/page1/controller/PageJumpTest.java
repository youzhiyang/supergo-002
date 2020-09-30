package com.supergo.page1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageJumpTest {

    /**
     * 页面跳转测试方法
     * @return
     */
    @RequestMapping("demo")
    public String demo() {
        System.out.println("进入controller中的demo方法！");
        /*注意：这里返回值有后缀名，如何省略后缀名后面有介绍*/
        return "main.html";
    }

    /**
     * 页面跳转测试方法
     * @return
     */
    @RequestMapping("demo1")
    public String demo1() {
        System.out.println("进入controller中的demo方法！");
        /*注意：这里返回值有后缀名，如何省略后缀名后面有介绍*/
        return "main";
    }
}
