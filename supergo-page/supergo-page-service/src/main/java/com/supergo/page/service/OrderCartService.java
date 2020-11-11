package com.supergo.page.service;

import com.supergo.manager.feign.ApiGoodsFeign;
import com.supergo.manager.feign.ApiOrderCartFeign;
import com.supergo.pojo.Ordercart;
import com.supergo.user.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    public int getItemStock(int itemId) {
        int itemStock = apiGoodsFeign.getItemStock(itemId);
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

    /**
     * 更新redis购物车数据
     */
    public void updateRedisOrderCart(HttpServletRequest request,int itemId, int num) {
        String clientId = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            //获取cookie信息
            Map<String, String> clientId1 = CookieUtil.readCookie(request, "clientId");
            clientId = clientId1.get("clientId");
        }
        apiOrderCartFeign.updateRedisOrderCart(itemId,num,clientId);
    }

    /**
     * 根据id删除
     * @param id
     */
    public void delete(long id) {
        apiOrderCartFeign.delete(id);
    }

    /**
     * 删除redis数据
     */
    public void deleteRedis(HttpServletRequest request,int itemId) {
        String clientId = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            //获取cookie信息
            Map<String, String> clientId1 = CookieUtil.readCookie(request, "clientId");
            clientId = clientId1.get("clientId");
        }
        apiOrderCartFeign.deleteRedis(itemId,clientId);
    }

    /**
     * 批量删除
     */
    public void deletePatch(String[] ids) {
        apiOrderCartFeign.deletePatch(ids);
    }

    /**
     * 批量删除redis数据
     */
    public void deleteRedisPatch(HttpServletRequest request,String[] ids) {
        String clientId = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            //获取cookie信息
            Map<String, String> clientId1 = CookieUtil.readCookie(request, "clientId");
            clientId = clientId1.get("clientId");
        }
        apiOrderCartFeign.deleteRedisPatch(ids,clientId);
    }
}
