package com.supergo.page.service;

import com.supergo.manager.feign.ApiGoodsFeign;
import com.supergo.manager.feign.ApiOrderCartFeign;
import com.supergo.pojo.Ordercart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/10/26.
 */
@Service
public class OrderCartService {

    @Autowired
    private ApiOrderCartFeign apiOrderCartFeign;
    @Autowired
    private ApiGoodsFeign apiGoodsFeign;

    /**
     * 更新购物车信息
     */
    public void updateOrderCart(int id,int num) {
        apiOrderCartFeign.updateOrderCart(id,num);
    }

    /**
     * 获取剩余库存信息
     * @return
     */
    public int getItemStock(long id) {
        //1、根据购物车id获取购物车信息
        Ordercart ordercart = apiOrderCartFeign.selectByPrimaryKey(id);
        //2、查询剩余库存信息
        int itemStock = apiGoodsFeign.getItemStock(ordercart.getItemId());
        return itemStock;
    }

    /**
     * 获取购物车列表数据
     * @return
     */
    public List<Map<Object,Object>> getOrderCartList() {
        List<Map<Object, Object>> orderCart = apiOrderCartFeign.getOrderCart();
        return orderCart;
    }
}
