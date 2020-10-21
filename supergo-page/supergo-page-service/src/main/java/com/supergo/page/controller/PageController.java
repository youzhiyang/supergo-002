package com.supergo.page.controller;

import com.supergo.http.HttpResult;
import com.supergo.page.service.PageService;
import com.supergo.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class PageController {

    @Autowired
    private PageService pageService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 生成商品模板
     * @param goodsId
     * @return
     * @throws IOException
     */
    @RequestMapping("/html/build/{goodsId}")
    public HttpResult buildHtml(@PathVariable Long goodsId, HttpServletRequest request) throws IOException {
        System.out.println("goodsId:   " + goodsId);
        HttpResult httpResult = pageService.buildGoodsPage(goodsId,request);
        System.out.println("httpResult:---2222   " + httpResult.getCode());
        return httpResult;
    }

    /**
     * 获取商品库存
     * @param goodsId
     * @return
     */
    @RequestMapping("/goods/stock/{goodsId}")
    public Map getGoodsStock(@PathVariable long goodsId) {
        Map itemStocks = pageService.getItemStocks(goodsId);
        return itemStocks;
    }

    /**
     * 未登录情况下添加购物车
     * @return
     */
    @RequestMapping("/goods/unloginAddOrderCart")
    public HttpResult unloginAddOrderCart(Integer itemId,String clientId,Integer num,Integer sellerId) {
        System.out.println("进入unloginAddOrderCart接口...");
        return pageService.unloginAddOrderCart(itemId, clientId, num, sellerId);
    }

    /**
     * 登录情况下添加购物车
     * @return
     */
    @RequestMapping("/goods/addOrderCart")
    public HttpResult addOrderCart(Integer itemId,Integer num,Integer sellerId) {
        System.out.println("进入addOrderCart接口...");
        return pageService.addOrderCart(request,itemId, num, sellerId);
    }

    /**
     * 用户登录情况下，显示购物车数据
     * @return
     */
    @RequestMapping("/goods/showOrderCart")
    public HttpResult showOrderCart() {
        System.out.println("进入showOrderCart接口...");
        return pageService.showOrderCart(request);
    }

    /**
     * 根据id获取库存信息
     * @param goodsId
     * @return
     */
    @RequestMapping("/goods/getItemByGoodsId")
    public HttpResult getItemByGoodsId(long goodsId) {
        System.out.println("进入getItemByGoodsId接口...");
        List<Item> itemList = pageService.getItemByGoodsId(goodsId);
        return HttpResult.ok(itemList);
    }

}
