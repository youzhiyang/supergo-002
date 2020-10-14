package com.supergo.manager.controller;

import com.supergo.manager.service.OrderCartService;
import com.supergo.pojo.Ordercart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 购物车controller
 */
@RestController
@RequestMapping("orderCart")
public class OrderCartController {

    @Autowired
    private OrderCartService orderCartService;

    /**
     * 用户登录情况下添加购物车
     * @param httpRequest
     * @param itemId
     * @param num
     * @param sellerId
     */
    @RequestMapping("/add/{item_id}/{client_id}/{seller_id}")
    public void addOrderCart(HttpServletRequest httpRequest, @PathVariable("item_id")int itemId,
                             @PathVariable int num,@PathVariable("seller_id")String sellerId) {
        Map<Object, Object> objectObjectMap = orderCartService.addOrderCart(httpRequest, itemId, num,sellerId);
    }

    /**
     * 用户未登录情况下添加购物车
     * @param itemId
     * @param num
     */
    @RequestMapping("/unloginAddOrderCart/{item_id}/{client_id}/{seller_id}/{num}")
    public Map<Object, Object> unloginAddOrderCart(@PathVariable("item_id")int itemId,@PathVariable("client_id")String clientId,
                             @PathVariable("num")int num,@PathVariable("seller_id")int sellerId) {
        System.out.println("进入unloginAddOrderCart接口...");
        Map<Object, Object> objectObjectMap = orderCartService.unloginAddOrderCart(itemId, num, sellerId, clientId);
        return objectObjectMap;
    }
}
