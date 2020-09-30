package com.supergo.manager.service.impl;

import com.supergo.manager.service.OrderCartService;
import com.supergo.pojo.Ordercart;
import com.supergo.mapper.ItemMapper;
import com.supergo.pojo.User;
import com.supergo.service.base.impl.BaseServiceImpl;
import com.supergo.user.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 购物车service
 */
@Service
public class OrderCartServiceImpl extends BaseServiceImpl<Ordercart> implements OrderCartService {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 添加购物车
     * @param request
     * @param itemId
     * @param num
     */
    @Override
    public Map<Object, Object> addOrderCart(HttpServletRequest request, int itemId, int num,String sellerId) {
        //获取剩余库存数量
        //int itemStock = itemService.getItemStock(itemId);
        User user = (User) request.getSession().getAttribute("user");
        //获取sku库存信息
        Map<String, String> skuMap = itemMapper.selectBySellerIdAndSkuId(itemId, sellerId);
        Map<Object, Object> ordercartMap= new HashMap<>();
        //如果用户未登入
        if(user == null) {
            Cookie[] cookies = request.getCookies();
            //获取添加商品数据
            for(int i = 0;i < cookies.length;i++) {
                String cookieStr = cookies[i].getValue();
                //如果为购物车cookie
                if(cookieStr.equals("CART_COOKIE")) {
                    //获取cookie中数据
                    ordercartMap = parseCookieValue(cookieStr);
                    //当cookie中存在购物车数据时
                    Map<Object, Object> cartDetail1 = addCartSku(ordercartMap, num, itemId, skuMap.toString());
                    break;
                }
            }
        } else {   //如果用户登入
            //redis取店铺缓存
            Map<Object, Object> cartInfo = stringRedisTemplate.opsForHash().entries("cart:" + user.getId() + ":info");
            //redis取上品缓存
            Map<Object, Object> cartDetail = stringRedisTemplate.opsForHash().entries("cart:" + user.getId() + ":detail");
            ordercartMap.put("cartInfo",cartInfo);
            ordercartMap.put("cartDetail",cartDetail);
            //向购物车添加sku
            Map<Object, Object> cartDetail1 = addCartSku(ordercartMap, num, itemId, skuMap.toString());

            //遍历保存购物车信息
            cartDetail1.forEach((k,v)->{
                int i = 1;
                //保存购物车信息
                stringRedisTemplate.opsForHash().put("cart:" + user.getId() + ":detail","sku"+i,v);
                i++;
            });
        }

        return ordercartMap;
    }

    /**
     * 解析cookie信息
     * @param cookieStr
     * @return
     */
    private Map<Object, Object> parseCookieValue(String cookieStr) {


        return null;
    }

    //向购物车添加sku
    private Map<Object, Object> addCartSku(Map<Object, Object> ordercartMap,int num,int itemId,String sku) {
        Map<Object, Object> cartDetail = (Map<Object, Object>) ordercartMap.get("cartDetail");
        for(Map.Entry<Object, Object> it : cartDetail.entrySet()){
            if(it.getKey().equals(itemId + "")) {
                String skuJson = (String)it.getValue();
                Map<String, String> skuMap = JsonUtils.jsonToMap(skuJson, String.class, String.class);
                //存在sku，sku相加
                skuMap.put("num",(skuMap.get(num) + num));
                cartDetail.put(it.getKey(),skuMap.toString());
            } else {
                //向购物车添加sku
                cartDetail.put(it.getKey(),sku);
            }
        }
        return cartDetail;
    }

}
