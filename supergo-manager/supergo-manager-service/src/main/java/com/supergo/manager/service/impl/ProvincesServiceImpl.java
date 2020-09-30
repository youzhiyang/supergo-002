package com.supergo.manager.service.impl;

import com.supergo.http.HttpResult;
import com.supergo.manager.service.CitiesService;
import com.supergo.manager.service.ProvincesService;
import com.supergo.pojo.Cities;
import com.supergo.pojo.Provinces;
import com.supergo.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 省操作实现类
 */
@Service
public class ProvincesServiceImpl extends BaseServiceImpl<Provinces> implements ProvincesService {

    @Autowired
    private Mapper<Provinces> mapper;


    /**
     * 查询省份列表
     * @return
     */
    @Override
    public List<Provinces> getProvincesList() {
        List<Provinces> provinces = mapper.selectAll();
        return provinces;
    }
}
