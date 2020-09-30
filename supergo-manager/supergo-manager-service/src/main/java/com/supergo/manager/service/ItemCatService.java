package com.supergo.manager.service;

import com.supergo.pojo.Itemcat;
import com.supergo.service.base.BaseService;

import java.util.List;

public interface ItemCatService extends BaseService<Itemcat> {

    List<Itemcat> getItemCatList();
}
