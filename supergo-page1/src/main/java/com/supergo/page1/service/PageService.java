package com.supergo.page1.service;

import com.alibaba.fastjson.JSON;
import com.supergo.http.HttpResult;
import com.supergo.manager.feign.ApiGoodsFeign;
import com.supergo.manager.feign.ApiItemCatFeign;
import com.supergo.page1.pojo.UserInfo;
import com.supergo.pojo.Goods;
import com.supergo.pojo.Goodsdesc;
import com.supergo.pojo.Item;
import com.supergo.pojo.Itemcat;
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
import java.util.List;
import java.util.Map;

@Service
public class PageService {

    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private ApiGoodsFeign goodsFeign;
    @Autowired
    private ApiItemCatFeign itemCatFeign;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
//    @Autowired
//    private GoddsLock goddsLock;

    /**
     * 生成静态页面的业务逻辑
     * @return
     * @throws IOException
     */
    public HttpResult statcPageBuild() throws IOException {
        FileWriter fileWriter = new FileWriter("D:\\用户目录\\下载\\supergo-002\\supergo-page\\src\\main\\resources\\goods\\hello.html");
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
        FileWriter fileWriter = new FileWriter("D:\\用户目录\\下载\\supergo-002\\supergo-page\\src\\main\\resources\\goods\\"+goodsId+".html");
        Context context = getGoodsData(goodsId,request);
        templateEngine.process("item", context, fileWriter);
        fileWriter.close();
        return HttpResult.ok();
    }
    public Context getGoodsData(Long goodsId,HttpServletRequest request){
        Context context = new Context();
        System.out.println("goods:  aaaaa");

        UserInfo userInfo = new UserInfo();
        Claims claims = (Claims) request.getAttribute("userInfo");
        userInfo.setId(claims.getId());
        userInfo.setUsername(claims.getSubject());
        context.setVariable("userInfo",userInfo);

        // Goods、
        Goods goods = goodsFeign.getGoodsById(goodsId);
        System.out.println("goods:  " + goodsId.toString());
//        //查询商品的分类  3个分类
        Itemcat itemCat1 = itemCatFeign.getItemCatById(goods.getCategory1Id());
        Itemcat itemCat2 = itemCatFeign.getItemCatById(goods.getCategory1Id());
        Itemcat itemCat3 = itemCatFeign.getItemCatById(goods.getCategory1Id());
        // GoodsDesc、
        //GoodsDesc goodsDesc = goodsDescMapper.selectByPrimaryKey(goodsId);
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
        // List<Item>
        List<Item> itemList = goodsFeign.getItemList(goodsId);
        context.setVariable("goods", goods);
        context.setVariable("goodsDesc", goodsDesc);
        context.setVariable("itemCat1", itemCat1);
        context.setVariable("itemCat2", itemCat2);
        context.setVariable("itemCat3", itemCat3);
        context.setVariable("itemList", itemList);

        return context;
    }

    /**
     * 查询商品sku库存量
     * @param goodsId
     * @return
     */
//    public Map getItemStocks(long goodsId) {
//        //防止缓存穿透,非法id直接返回
//        if(goodsId <= 0) {
//            Map<Object, Object> hashMap = new HashMap<>();
//            hashMap.put("0","0");
//            return hashMap;
//        }
//        //保证同时只能有一个线程查询同一个商品
//        ReentrantLock lock = goddsLock.getLock(goodsId);
//        //解决缓存击穿（缓存到期，缓存查不到数据，数据库可以查的到）
//        if(lock.tryLock()) {
//            //先查询redis，如果redis中有缓存数据，直接返回
//            Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("stock:" + Long.toString(goodsId));
//            //判断商品是否取到库存数据
//            if(entries == null || entries.isEmpty()) {
//                //添加空值缓存,防止缓存穿透
//                stringRedisTemplate.opsForHash().put("stock:" + Long.toString(goodsId),"0", "0");
//                //设置过期时间
//                stringRedisTemplate.expire("stock:" + Long.toString(goodsId),5, TimeUnit.MINUTES);
//                Map<Object, Object> hashMap = new HashMap<>();
//                hashMap.put("0","0");
//                return hashMap;
//            }
//            //如果redis中没有数据，调用微服务查询库存数据
//            if(entries != null && !entries.isEmpty()) {
//                //刷新缓存时间
//                stringRedisTemplate.expire("stock:" + Long.toString(goodsId),1, TimeUnit.DAYS);
//                return entries;
//            }
//            //把库存缓存到redis中
//            List<Item> itemList = goodsFeign.getItemList(goodsId);
//            HashMap<Object, Object> result = new HashMap<>();
//            itemList.forEach(i->{
//                result.put(Long.toString(i.getId()),i.getNum());
//                stringRedisTemplate.opsForHash().put("stock:" + Long.toString(goodsId),Long.toString(i.getId()),i.getNum().toString());
//            });
//            //设置缓存的过期时间，提高缓存的利用率
//            stringRedisTemplate.expire("stock:" + Long.toString(goodsId),1, TimeUnit.DAYS);
//            lock.unlock();
//            goddsLock.removeLock(goodsId);
//            //返回结果
//            return result;
//        } else {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return getItemStocks(goodsId);
//        }
//    }
}
