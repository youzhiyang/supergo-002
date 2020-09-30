package com.supergo.manager.service;

import com.supergo.pojo.Provinces;
import com.supergo.service.base.BaseService;

import java.util.List;

/**
 * Created by Administrator on 2020/8/17 0017.
 */
public interface ProvincesService extends BaseService<Provinces>{

    /**
     * 获取省列表
     * @return
     */
    public List<Provinces> getProvincesList();
}
