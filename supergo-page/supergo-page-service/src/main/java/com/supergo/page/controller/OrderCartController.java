package com.supergo.page.controller;

import com.supergo.http.HttpResult;
import com.supergo.page.service.OrderCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/10/26.
 */
@RestController
@RequestMapping("orderCart")
public class OrderCartController {

    @Autowired
    private OrderCartService orderCartService;

    /**
     * 更新购物车信息
     */
    @RequestMapping("/updateOrderCart")
    public HttpResult updateOrderCart(int id, int num) {
        orderCartService.updateOrderCart(id,num);
        return HttpResult.ok();
    }

    /**
     * 获取剩余库存信息
     * @return
     */
    @RequestMapping("/getItemStock")
    public HttpResult getItemStock(long id) {
        int itemStock = orderCartService.getItemStock(id);
        return HttpResult.ok(itemStock);
    }

    /**
     * 获取剩余库存信息
     * @return
     */
    @RequestMapping("/getOrderCartList")
    public HttpResult getOrderCartList() {
        List<Map<Object, Object>> orderCartList = orderCartService.getOrderCartList();
        return HttpResult.ok(orderCartList);
    }
}
