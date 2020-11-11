package com.supergo.page.controller;

import com.supergo.http.HttpResult;
import com.supergo.page.service.OrderCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private HttpServletRequest request;

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
    public HttpResult getItemStock(int itemId) {
        int itemStock = orderCartService.getItemStock(itemId);
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

    /**
     * 更新redis信息
     * @param itemId
     * @param num
     */
    @RequestMapping("/updateRedisOrderCart")
    public HttpResult updateRedisOrderCart(int itemId, int num) {
        System.out.println("进入updateRedisOrderCart接口...");
        orderCartService.updateRedisOrderCart(request,itemId,num);
        return HttpResult.ok();
    }

    /**
     * 根据id删除
     * @param id
     */
    @RequestMapping("/delete")
    public HttpResult delete(long id) {
        System.out.println("进入delete接口...");
        orderCartService.delete(id);
        return HttpResult.ok();
    }

    /**
     * 删除redis数据
     */
    @RequestMapping("/deleteRedis")
    public HttpResult deleteRedis(int itemId) {
        orderCartService.deleteRedis(request,itemId);
        return HttpResult.ok();
    }

    /**
     * 批量删除
     */
    @RequestMapping("/deletePatch")
    public HttpResult deletePatch(@RequestParam(value="ids[]") String[] ids) {
        System.out.println("进入deletePatch接口....");
        orderCartService.deletePatch(ids);
        return HttpResult.ok();
    }

    /**
     * 批量删除redis数据
     */
    @RequestMapping("/deleteRedisPatch")
    public void deleteRedisPatch(@RequestParam(value="ids[]") String[] ids) {
        System.out.println("进入deletePatch接口....");
        for(int i = 0;i < ids.length;i++) {
            System.out.println("ids:   " + ids[i]);
        }
        orderCartService.deleteRedisPatch(request,ids);
    }
}


