package com.supergo.manager.controller;

import com.supergo.http.HttpResult;
import com.supergo.manager.service.AreasService;
import com.supergo.manager.service.CitiesService;
import com.supergo.manager.service.ProvincesService;
import com.supergo.pojo.Areas;
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
    @Autowired
    private AreasService areasService;

    /**
     * 查询地址信息
     * @return
     */
    @RequestMapping("/getProvincesList")
    public List<Provinces> getProvincesList() {
        //获取省级信息
        List<Provinces> provincesList = provincesService.getProvincesList();
        Cities cities = new Cities();
        List<Cities> citiesList = null;
        for(Provinces provinces : provincesList) {
            cities.setProvinceid(provinces.getProvinceid());
            //根据省级id获取城市信息
            citiesList = citiesService.findByWhere(cities);
            provinces.setCitiesList(citiesList);
            List<Areas> areasList = null;
            for(Cities cities1 : citiesList) {
                Areas areas = new Areas();
                areas.setCityid(cities1.getCityid());
                //根据城市id获取区域信息
                areasList = areasService.findByWhere(areas);
                cities1.setAreasList(areasList);
            }
        }
        return provincesList;
    }
}
