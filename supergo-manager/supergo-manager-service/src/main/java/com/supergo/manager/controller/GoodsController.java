package com.supergo.manager.controller;

import com.supergo.pojo.Goods;
import com.supergo.pojo.Goodsdesc;
import com.supergo.pojo.Item;
import com.supergo.manager.service.GoodsDescService;
import com.supergo.manager.service.GoodsService;
import com.supergo.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsDescService goodsDescService;
    @Autowired
    private ItemService itemService;

    @GetMapping("/{goodsId}")
    public Goods getGoodsById(@PathVariable long goodsId) {
        Goods goods = goodsService.findOne(goodsId);
        return goods;
    }
    @GetMapping("/desc/{goodsId}")
    public Goodsdesc getGoodsDescById(@PathVariable long goodsId) {
        Goodsdesc goodsdesc = goodsDescService.findOne(goodsId);
        return goodsdesc;
    }
    @GetMapping("/item/{goodsId}")
    public List<Item> getItemList(@PathVariable long goodsId) {
        List<Item> items = itemService.skuList(goodsId);
        return items;
    }

    /**
     * 获取剩余库存信息
     * @param itemId
     * @return
     */
    @GetMapping("/getItemStock/{itemId}")
    public int getItemStock(@PathVariable("itemId") long itemId) {
        int itemStock = itemService.getItemStock(itemId);
        return itemStock;
    }

}
