package com.supergo.manager.feign;

import com.supergo.pojo.Goods;
import com.supergo.pojo.Goodsdesc;
import com.supergo.pojo.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(serviceId = "supergo-manager")
public interface ApiGoodsFeign {
    @GetMapping("/goods/{goodsId}")
    public Goods getGoodsById(@PathVariable("goodsId") long goodsId);
    @GetMapping("/goods/desc/{goodsId}")
    public Goodsdesc getGoodsDescById(@PathVariable("goodsId") long goodsId);
    @GetMapping("/goods/item/{goodsId}")
    public List<Item> getItemList(@PathVariable("goodsId") long goodsId);

}
