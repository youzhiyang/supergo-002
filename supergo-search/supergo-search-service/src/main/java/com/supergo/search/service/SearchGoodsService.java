package com.supergo.search.service;

import com.supergo.http.HttpResult;

public interface SearchGoodsService {

    /**
     * 查询数据库，将所有商品导入索引库
     */
    public HttpResult importGoods();
}
