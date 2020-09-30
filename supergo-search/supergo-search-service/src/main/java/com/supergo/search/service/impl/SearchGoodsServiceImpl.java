package com.supergo.search.service.impl;

import com.supergo.http.HttpResult;
import com.supergo.search.entity.GoodsEntity;
import com.supergo.search.mapper.GoodsMapper;
import com.supergo.search.repository.GoodsRepository;
import com.supergo.search.service.SearchGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("searchGoodsServiceImpl")
public class SearchGoodsServiceImpl implements SearchGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsRepository goodsRepostory;

    /**
     * 查询数据库，将所有商品导入索引库
     */
    public HttpResult importGoods() {
        //获取商品列表
        List<GoodsEntity> goodsList = goodsMapper.getGoodsList();
        //把商品列表写入索引库
        System.out.println("导入数据开始.....");
        goodsList.forEach(g->goodsRepostory.save(g));
        System.out.println("导入数据结束.....");
        return HttpResult.ok();
    }

}
