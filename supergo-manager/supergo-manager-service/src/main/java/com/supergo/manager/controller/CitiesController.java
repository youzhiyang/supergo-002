package com.supergo.manager.controller;

import com.supergo.http.HttpResult;
import com.supergo.manager.service.CitiesService;
import com.supergo.page.PageResult;
import com.supergo.pojo.Cities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cities")
public class CitiesController {

    @Autowired
    private CitiesService citiesService;

    @RequestMapping("/{citiesId}")
    public Cities getCitiesById(@PathVariable long citiesId) {
        Cities cities = citiesService.findOne(citiesId);
        return cities;
    }

    /**
     * 获取城市列表
     * @param page
     * @param size
     * @param cities
     * @return
     */
    @RequestMapping("/getCitiesList/{page}/{size}")
    public HttpResult getCitiesList(@PathVariable int page, @PathVariable int size, @RequestBody(required = false) Cities cities) {
        PageResult page1 = citiesService.findPage(page, size, cities);
        return HttpResult.ok(page1);
    }

}
