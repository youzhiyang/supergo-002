package com.supergo.manager.controller;

import com.supergo.http.HttpResult;
import com.supergo.manager.service.CitiesService;
import com.supergo.manager.service.ProvincesService;
import com.supergo.pojo.Cities;
import com.supergo.pojo.Provinces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2020/8/18 0018.
 */
@RestController
@RequestMapping("/provinces")
public class ProvicesController {

    @Autowired
    private CitiesService citiesService;
    @Autowired
    private ProvincesService provincesService;

    @RequestMapping("/getProvincesList")
    public HttpResult getProvincesList() {

        List<Provinces> provincesList = provincesService.getProvincesList();
        Cities cities = new Cities();
        for(Provinces provinces : provincesList) {
            citiesService.findByWhere(cities);
            cities.setId(provinces.getId());
            provinces.setCitiesList(citiesService.findByWhere(cities));
        }
        return HttpResult.ok(provincesList);
    }
}
