package com.supergo.page.service;

import com.alibaba.fastjson.JSON;
import com.supergo.http.HttpResult;
import com.supergo.manager.feign.ApiGoodsFeign;
import com.supergo.manager.feign.ApiItemCatFeign;
import com.supergo.manager.feign.ApiOrderCartFeign;
import com.supergo.manager.feign.ApiProvincesFeign;
import com.supergo.page.config.GoddsLock;
import com.supergo.page.util.Const;
import com.supergo.page.util.FileUtil;
import com.supergo.pojo.*;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class PageService {

    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private ApiGoodsFeign goodsFeign;
    @Autowired
    private ApiItemCatFeign itemCatFeign;
    @Autowired
    private ApiOrderCartFeign apiOrderCartFeign;
    @Autowired
    private ApiProvincesFeign apiProvincesFeign;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private GoddsLock goddsLock;

    /**
     * 生成静态页面的业务逻辑
     * @return
     * @throws IOException
     */
    public HttpResult statcPageBuild() throws IOException {
        FileWriter fileWriter = new FileWriter("E:\\supergo\\supergo-002\\supergo-page\\supergo-page-service\\src\\main\\resources\\goods\\hello.html");
        Context context = new Context();
        context.setVariable("hello", "hello spring and thymeleaf!");
        templateEngine.process("hello", context, fileWriter);
        return HttpResult.ok();
    }

    /**
     * 生成静态页面业务逻辑
     * @param goodsId 商品id
     * @param request
     * @return 生成结果
     * @throws IOException
     */
    public HttpResult buildGoodsPage(long goodsId, HttpServletRequest request) throws IOException {
        FileWriter fileWriter = new FileWriter(Const.pagePath +goodsId+Const.sufferHtml);
        Context context = getGoodsData(goodsId,request);
        //每次创建模板前删除原来的模板
        boolean b = FileUtil.deleteFile(Const.pagePath + goodsId + Const.sufferHtml);
        if(b) {
            System.out.println("删除模板成功！");
            templateEngine.process("item", context, fileWriter);
        } else {
            System.out.println("删除模板失败！");
        }

        fileWriter.close();
        return HttpResult.ok();
    }

    /**
     * 获取商品模板数据
     * @param goodsId
     * @param request
     * @return
     */
    public Context getGoodsData(Long goodsId,HttpServletRequest request){
        Context context = new Context();
        User userInfo = new User();
        Claims claims = (Claims) request.getAttribute("userInfo");
        System.out.println("claims:  " + claims != null);
        String token = null;
        if(claims != null) {
            //如果未登入用户信息设置为null
            userInfo.setId(Long.valueOf(claims.getId()));
            userInfo.setUsername(claims.getSubject());
            System.out.println("claims:  " + claims.toString());
            //获取token值
            Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("loginInfo" + claims.getId());
            System.out.println(entries);
            token = (String) entries.get(claims.getId());
        }

        //获取登入用户信息
        context.setVariable("userInfo",userInfo);
        if(token != null) {
            context.setVariable("bearerToken","Bearer " + token);
        } else {
            context.setVariable("bearerToken",token);
        }
        // 获取商品信息
        Goods goods = goodsFeign.getGoodsById(goodsId);
        System.out.println("goods:  " + goodsId.toString());
        //查询商品的分类3个分类
        Itemcat itemCat1 = itemCatFeign.getItemCatById(goods.getCategory1Id());
        Itemcat itemCat2 = itemCatFeign.getItemCatById(goods.getCategory1Id());
        Itemcat itemCat3 = itemCatFeign.getItemCatById(goods.getCategory1Id());
        // GoodsDesc、
        //GoodsDesc goodsDesc = goodsDescMapper.selectByPrimaryKey(goodsId);
        //获取商品描述信息
        Goodsdesc goodsDesc = goodsFeign.getGoodsDescById(goodsId);
        //取图片列表
        String jsonImages = goodsDesc.getItemImages();
        if (StringUtils.isNotBlank(jsonImages)) {
            try {
                List<Map> imagesList = JSON.parseArray(jsonImages, Map.class);
                context.setVariable("itemImageList", imagesList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //取属性信息
        String jsonCustomAttributeItems = goodsDesc.getCustomAttributeItems();
        if (StringUtils.isNotBlank(jsonCustomAttributeItems)) {
            try {
                List<Map> customAttributeList = JSON.parseArray(jsonCustomAttributeItems, Map.class);
                context.setVariable("customAttributeList", customAttributeList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //提取规格数据
        String jsonSpecificationItems = goodsDesc.getSpecificationItems();
        if (StringUtils.isNotBlank(jsonSpecificationItems)) {
            try {
                List<Map> specificationItems = JSON.parseArray(jsonSpecificationItems, Map.class);
                context.setVariable("specificationList", specificationItems);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //获取地址信息
        List<Provinces> provincesList = apiProvincesFeign.getProvincesList();

        // 查询库存列表
        List<Item> itemList = goodsFeign.getItemList(goodsId);
        context.setVariable("goods", goods);
        context.setVariable("goodsDesc", goodsDesc);
        context.setVariable("itemCat1", itemCat1);
        context.setVariable("itemCat2", itemCat2);
        context.setVariable("itemCat3", itemCat3);
        context.setVariable("itemList", itemList);
        context.setVariable("provincesList",provincesList);

        return context;
    }

    /**
     * 查询商品sku库存量
     * @param goodsId
     * @return
     */
    public Map getItemStocks(long goodsId) {
        //防止缓存穿透,非法id直接返回
        if(goodsId <= 0) {
            Map<Object, Object> hashMap = new HashMap<>();
            hashMap.put("0","0");
            return hashMap;
        }
        //保证同时只能有一个线程查询同一个商品
        ReentrantLock lock = goddsLock.getLock(goodsId);
        //解决缓存击穿（缓存到期，缓存查不到数据，数据库可以查的到）
        if(lock.tryLock()) {
            //先查询redis，如果redis中有缓存数据，直接返回
            Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("stock:" + Long.toString(goodsId));
            //判断商品是否取到库存数据
            if(entries == null || entries.isEmpty()) {
                //添加空值缓存,防止缓存穿透
                stringRedisTemplate.opsForHash().put("stock:" + Long.toString(goodsId),"0", "0");
                //设置过期时间
                stringRedisTemplate.expire("stock:" + Long.toString(goodsId),5, TimeUnit.MINUTES);
                Map<Object, Object> hashMap = new HashMap<>();
                hashMap.put("0","0");
                return hashMap;
            }
            //如果redis中没有数据，调用微服务查询库存数据
            if(entries != null && !entries.isEmpty()) {
                //刷新缓存时间
                stringRedisTemplate.expire("stock:" + Long.toString(goodsId),1, TimeUnit.DAYS);
                return entries;
            }
            //把库存缓存到redis中
            List<Item> itemList = goodsFeign.getItemList(goodsId);
            HashMap<Object, Object> result = new HashMap<>();
            itemList.forEach(i->{
                result.put(Long.toString(i.getId()),i.getNum());
                stringRedisTemplate.opsForHash().put("stock:" + Long.toString(goodsId),Long.toString(i.getId()),i.getNum().toString());
            });
            //设置缓存的过期时间，提高缓存的利用率
            stringRedisTemplate.expire("stock:" + Long.toString(goodsId),1, TimeUnit.DAYS);
            lock.unlock();
            goddsLock.removeLock(goodsId);
            //返回结果
            return result;
        } else {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getItemStocks(goodsId);
        }
    }

    /**
     * 用户未登录情况下添加购物车
     */
    public HttpResult unloginAddOrderCart(int itemId,String clientId,int num,int sellerId) {
        try {
            FileWriter fileWriter = new FileWriter(Const.pagePath + "unloginAddOrderCart" + Const.sufferHtml);
            Map<Object, Object> skuMap = apiOrderCartFeign.unloginAddOrderCart(itemId, clientId, num, sellerId);
            Context context = new Context();
            User userInfo = new User();
            //获取登入用户信息
            context.setVariable("userInfo",userInfo);
            context.setVariable("skuMap",skuMap);
            //每次创建模板前删除原来的模板
            boolean b = FileUtil.deleteFile(Const.pagePath + "unloginAddOrderCart" + Const.sufferHtml);
            System.out.println("删除文件成功！");
            templateEngine.process("unloginAddOrderCart", context, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return HttpResult.ok();
    }

    /**
     * 登录情况下添加购物车
     * @param itemId
     * @param num
     * @param sellerId
     * @return
     */
    public HttpResult addOrderCart(HttpServletRequest request,Integer itemId,Integer num, Integer sellerId) {
        try {
            User userInfo = new User();
            Claims claims = (Claims) request.getAttribute("userInfo");
            System.out.println("claims:  " + claims != null);
            String token = null;
            if(claims != null) {
                //如果未登入用户信息设置为null
                userInfo.setId(Long.valueOf(claims.getId()));
                userInfo.setUsername(claims.getSubject());
                System.out.println("claims:  " + claims.toString());
                //获取token值
                Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("loginInfo" + claims.getId());
                System.out.println(entries);
                token = (String) entries.get(claims.getId());
            }

            FileWriter fileWriter = new FileWriter(Const.pagePath + "unloginAddOrderCart" + Const.sufferHtml);
            System.out.println("1---------------------");
            Map<Object, Object> skuMap = apiOrderCartFeign.addOrderCart(itemId, num, sellerId);
            System.out.println("122---------------------");
            Context context = new Context();
            //获取登入用户信息
            context.setVariable("userInfo",userInfo);
            context.setVariable("bearerToken","Bearer " + token);
            context.setVariable("skuMap",skuMap);
            //每次创建模板前删除原来的模板
            boolean b = FileUtil.deleteFile(Const.pagePath + "unloginAddOrderCart" + Const.sufferHtml);
            System.out.println("删除文件成功！");
            templateEngine.process("unloginAddOrderCart", context, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
