package com.supergo.page.service;

import com.supergo.manager.feign.ApiGoodsFeign;
import com.supergo.manager.feign.ApiProvincesFeign;
import com.supergo.pojo.Provinces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2020/10/4.
 */
@Service
public class ProvincesServices {

    @Autowired
    private ApiProvincesFeign apiProvincesFeign;

    public List<Provinces> getProvincesList() {
        return apiProvincesFeign.getProvincesList();
    }
}
