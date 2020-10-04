package com.supergo.page.controller;

import com.supergo.http.HttpResult;
import com.supergo.page.service.ProvincesServices;
import com.supergo.pojo.Provinces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2020/10/4.
 */
@RestController
@RequestMapping("/provinces")
public class ProvincesController {

    @Autowired
    private ProvincesServices provincesServices;

    /**
     * 获取城市列表信息
     * @return
     * @throws IOException
     */
    @RequestMapping("/getProvincesList")
    public HttpResult getProvincesList() throws IOException {
        System.out.println("进入getProvincesList接口...");
        List<Provinces> provincesList = provincesServices.getProvincesList();
        return HttpResult.ok(provincesList);
    }
}
