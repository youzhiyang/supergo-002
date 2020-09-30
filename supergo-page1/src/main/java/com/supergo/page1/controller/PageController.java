package com.supergo.page1.controller;

import com.supergo.http.HttpResult;
import com.supergo.page1.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@CrossOrigin(origins = "http://domain2.com", maxAge = 3600)
@RestController
public class PageController {

    @Autowired
    private PageService pageService;

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
        return httpResult;
    }

    /**
     * 获取商品库存
     * @param goodsId
     * @return
     */
//    @RequestMapping("/goods/stock/{goodsId}")
//    public Map getGoodsStock(@PathVariable long goodsId) {
//        Map itemStocks = pageService.getItemStocks(goodsId);
//        return itemStocks;
//    }

}
