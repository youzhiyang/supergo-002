package com.supergo.manager.controller;

import com.supergo.manager.service.OrderCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
     * 添加购物车
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

//    @RequestMapping("/add/{item_id}/{num}")
//    public void addCart(HttpServletRequest httpRequest, @PathVariable("item_id")int itemId,
//                             @PathVariable("num")int num) {
//        //List<Ordercart> ordercartList = orderCartService.addOrderCart(httpRequest, itemId, sellerId);
//
//    }
}
