package com.supergo.manager.controller;

import com.supergo.manager.service.OrderCartService;
import com.supergo.pojo.Ordercart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @Autowired
    private HttpServletRequest httpRequest;

    /**
     * 用户登录情况下添加购物车
     * @param itemId
     * @param num
     * @param sellerId
     */
    @RequestMapping("/addOrderCart/{item_id}/{num}/{seller_id}")
    public Map<Object, Object> addOrderCart( @PathVariable("item_id")int itemId,
                             @PathVariable("num") int num,@PathVariable("seller_id")int sellerId) {
        System.out.println("进入addOrderCart接口...");
        Map<Object, Object> objectObjectMap = orderCartService.addOrderCart(httpRequest, itemId, num,sellerId);
        return objectObjectMap;
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

    /**
     * 用户登录情况下，查询购物车
     * @return
     */
    @RequestMapping("/getOrderCart")
    public List<Map<Object,Object>> getOrderCart() {
        List<Map<Object,Object>> orderCart = orderCartService.getOrderCart();
        return orderCart;
    }

    /**
     * 更新购物车数据
     */
    @RequestMapping("/updateOrderCart/{id}/{num}")
    public void updateOrderCart(@PathVariable("id")int id,@PathVariable("num")int num) {
        orderCartService.updateOrderCart(id,num);
    }

    /**
     * 根据主键查询购物车
     */
    @RequestMapping("/selectByPrimaryKey/{id}")
    public Ordercart selectByPrimaryKey(@PathVariable("id") long id) {
        return orderCartService.selectByPrimaryKey(id);
    }
}
