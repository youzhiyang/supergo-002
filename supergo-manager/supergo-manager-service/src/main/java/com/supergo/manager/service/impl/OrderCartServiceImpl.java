package com.supergo.manager.service.impl;

import com.supergo.manager.service.OrderCartService;
import com.supergo.mapper.ItemMapper;
import com.supergo.mapper.OrdercartMapper;
import com.supergo.pojo.Ordercart;
import com.supergo.pojo.User;
import com.supergo.service.base.impl.BaseServiceImpl;
import com.supergo.user.utils.JsonUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 购物车service
 */
@Service
public class OrderCartServiceImpl extends BaseServiceImpl<Ordercart> implements OrderCartService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private OrdercartMapper ordercartMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 添加购物车
     * @param request
     * @param itemId
     * @param num
     */
    @Override
    public Map<Object, Object> addOrderCart(HttpServletRequest request, int itemId, int num,int sellerId) {

        User userInfo = new User();
        Claims claims = (Claims)request.getAttribute("userInfo");
        System.out.println(claims.toString());

        //获取sku库存信息
        Map<Object, Object> skuMap = itemMapper.selectBySellerIdAndSkuId(itemId, sellerId);
        System.out.println(skuMap.toString());
        //1、判断购物车是否存在该商品
        Ordercart ordercart = ordercartMapper.selectGoodsIsExist(itemId, Integer.parseInt(claims.getId()), sellerId);
        //如果购物车已有该商品
        if(ordercart != null) {
            //商品数量相加
            ordercart.setNum(ordercart.getNum() + num);
            update(ordercart);
        } else {
            ordercart = skuToOrderCart(skuMap, Integer.parseInt(claims.getId()), num);
            //保存该商品信息
            add(ordercart);
        }

        skuMap.put("selectedNum",num);
        return skuMap;
    }

    private Ordercart skuToOrderCart(Map<Object, Object> skuMap,int userId,int num ) {
        Ordercart ordercart = new Ordercart();
        ordercart.setItemId((Long) skuMap.get("item_id"));
        ordercart.setGoodsId((Long) skuMap.get("goods_id"));
        ordercart.setUserId((long) userId);
        ordercart.setSellerId((Long) skuMap.get("seller_id"));
        ordercart.setGoodsName((String) skuMap.get("goods_name"));
        ordercart.setNum(num);
        ordercart.setPrice((BigDecimal) skuMap.get("price"));
        ordercart.setStatus(0);
        return ordercart;
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
        System.out.println(cartDetail);
        //如果购物车已经存在
        Map<Object, Object> cartDetail1 = null;
        if(!cartDetail.isEmpty()) {
            //向购物车添加sku
            cartDetail1 = addCartSku(cartDetail, num, itemId, skuMap,clientId);
        } else {
            skuMap.put("num",String.valueOf(num));
            //如果不存在购物车，初始化购物车信息
            stringRedisTemplate.opsForHash().put("cart:" + clientId + ":detail",itemId + "",JsonUtils.objectToJson(skuMap));
            //设置过期时间
            stringRedisTemplate.expire("cart:" + clientId + ":detail",3 , TimeUnit.DAYS);
        }
        skuMap.put("selectedNum",num);
        return skuMap;
    }

    //向购物车添加sku
    private Map<Object, Object> addCartSku(Map<Object, Object> cartDetail,int num,int itemId,Map<Object, Object> skuMap,String clientId) {
        //判断购物车中是否存在该商品
        boolean isExist = false;
        for(Map.Entry<Object, Object> it : cartDetail.entrySet()){
            //如果购物车已经存在该商品
            if(it.getKey().equals(itemId + "")) {
                isExist = true;
                String skuJson = (String)it.getValue();
                Map<String, String> skuMap1 = JsonUtils.jsonToMap(skuJson, String.class, String.class);
                //商品数量相加
                skuMap.put("num",(Integer.parseInt(skuMap1.get("num")) + num));
                //如果不存在购物车，初始化购物车信息
                stringRedisTemplate.opsForHash().put("cart:" + clientId + ":detail",itemId + "",JsonUtils.objectToJson(skuMap));
                //设置过期时间
                stringRedisTemplate.expire("cart:" + clientId + ":detail",3 , TimeUnit.DAYS);
            } else {
                String skuJson = (String) it.getValue();
                //如果不存在购物车，初始化购物车信息
                stringRedisTemplate.opsForHash().put("cart:" + clientId + ":detail",it.getKey() + "",skuJson);
                //设置过期时间
                stringRedisTemplate.expire("cart:" + clientId + ":detail",3 , TimeUnit.DAYS);
            }
        }
        //如果购物车不存在该商品
        if(!isExist) {
            skuMap.put("num",num);
            //如果不存在购物车，初始化购物车信息
            stringRedisTemplate.opsForHash().put("cart:" + clientId + ":detail",itemId + "",JsonUtils.objectToJson(skuMap));
            //设置过期时间
            stringRedisTemplate.expire("cart:" + clientId + ":detail",3 , TimeUnit.DAYS);
        }
        return cartDetail;
    }

    /**
     * 用户登录情况下获取购物车数据
     */
    public List<Map<Object,Object>> getOrderCart() {
        List<Map<Object,Object>> orderCart = ordercartMapper.getOrderCart();
        return orderCart;
    }

    /**
     * 用户未登录情况下获取购物车数据
     */
    public List<Map<Object,Object>> getUnloginOrderCart(String clientId) {
        //redis取商品缓存
        Map<Object, Object> cartDetail = stringRedisTemplate.opsForHash().entries("cart:" + clientId + ":detail");
        List<Map<Object, Object>> list = new ArrayList<>();
        cartDetail.forEach((k,v) -> {
            String value = (String) v;
            Map<Object, Object> objectObjectMap = JsonUtils.jsonToMap(value, Object.class, Object.class);
            list.add(objectObjectMap);
        });
        System.out.println(list);
        return list;
    }

    /**
     * 更新购物车数据
     */
    public void updateOrderCart(int id,int num) {
        Ordercart ordercart = new Ordercart();
        ordercart.setNum(num);
        ordercart.setId((long) id);
        //1、根据id查询购物车信息
        ordercartMapper.updateByPrimaryKeySelective(ordercart);
    }

    /**
     * 更新redis购物车数据
     */
    public void updateRedisOrderCart(int itemId,int num,String clientId) {
        //redis取商品缓存
        Map<Object, Object> cartDetail = stringRedisTemplate.opsForHash().entries("cart:" + clientId + ":detail");
        //获取到对应的json数据
        String skuJson = (String) cartDetail.get(itemId + "");
        Map<String, String> skuMap = JsonUtils.jsonToMap(skuJson, String.class, String.class);
        skuMap.put("num",String.valueOf(num));
        //如果不存在购物车，初始化购物车信息
        stringRedisTemplate.opsForHash().put("cart:" + clientId + ":detail",itemId + "",JsonUtils.objectToJson(skuMap));
        //设置过期时间
        stringRedisTemplate.expire("cart:" + clientId + ":detail",3 , TimeUnit.DAYS);
    }

    /**
     * 根据主键查询购物车
     */
    public Ordercart selectByPrimaryKey(long id) {
        return ordercartMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id删除
     */
    public void delete(long id) {
        Ordercart ordercart = new Ordercart();
        ordercart.setId(id);
        ordercartMapper.delete(ordercart);
    }

    /**
     * 删除redis购物车数据
     * @param itemId
     */
    public void deleteRedis(int itemId,String clientId) {
        stringRedisTemplate.opsForHash().delete("cart:" + clientId + ":detail",String.valueOf(itemId));
    }

    /**
     * 批量删除
     */
    public void deletePatch(String[] ids) {
        for(int i = 0;i < ids.length;i++) {
            Ordercart ordercart = new Ordercart();
            ordercart.setItemId(Long.parseLong(ids[i]));
            ordercartMapper.delete(ordercart);
        }
    }

    /**
     * 批量删除redis购物车数据
     */
    public void deleteRedisPatch(String[] ids,String clientId) {
        for(int i = 0;i < ids.length;i++) {
            stringRedisTemplate.opsForHash().delete("cart:" + clientId + ":detail",String.valueOf(ids[i]));
        }
    }
}
