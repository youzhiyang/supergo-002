package com.supergo.manager.service.impl;

import com.supergo.manager.service.OrderCartService;
import com.supergo.pojo.Ordercart;
import com.supergo.mapper.ItemMapper;
import com.supergo.pojo.User;
import com.supergo.service.base.impl.BaseServiceImpl;
import com.supergo.user.utils.JsonUtils;
import io.jsonwebtoken.Claims;
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


        return null;
    }

    /**
     * 用户未登录情况下添加购物车
     * @return
     */
    public Map<Object, Object> unloginAddOrderCart(int itemId, int num,int sellerId,String clientId) {
        //获取sku库存信息
        Map<Object, Object> skuMap = itemMapper.selectBySellerIdAndSkuId(itemId, sellerId);
        //redis取店铺缓存
//        Map<Object, Object> cartInfo = stringRedisTemplate.opsForHash().entries("cart:" + clientId + ":info");
        //redis取商品缓存
        Map<Object, Object> cartDetail = stringRedisTemplate.opsForHash().entries("cart:" + clientId + ":detail");
        //如果购物车已经存在
        Map<Object, Object> cartDetail1 = null;
        if(!cartDetail.isEmpty()) {
            //向购物车添加sku
            cartDetail1 = addCartSku(cartDetail, num, itemId, skuMap);
            //保存购物车信息
            cartDetail1.forEach((k,v)->{
                //保存购物车信息
                stringRedisTemplate.opsForHash().put("cart:" + clientId + ":detail",k,JsonUtils.objectToJson(v));
            });
        } else {
            skuMap.put("num",String.valueOf(num));
            //如果不存在购物车，初始化购物车信息
            stringRedisTemplate.opsForHash().put("cart:" + clientId + ":detail",itemId + "",JsonUtils.objectToJson(skuMap));
        }
        skuMap.put("selectedNum",num);
        return skuMap;
    }

    //向购物车添加sku
    private Map<Object, Object> addCartSku(Map<Object, Object> cartDetail,int num,int itemId,Map<Object, Object> skuMap) {
        for(Map.Entry<Object, Object> it : cartDetail.entrySet()){
            //如果购物车已经存在该商品
            if(it.getKey().equals(itemId + "")) {
                String skuJson = (String)it.getValue();
                Map<String, String> skuMap1 = JsonUtils.jsonToMap(skuJson, String.class, String.class);
                //商品数量相加
                skuMap.put("num",(Integer.parseInt(skuMap1.get("num")) + num));
                cartDetail.put(it.getKey(),skuMap);
            } else {
                skuMap.put("num",num);
                //如果购物车中不存在该商品，将商品添加进入购物车
                cartDetail.put(itemId,skuMap);
            }
        }
        return cartDetail;
    }

}
