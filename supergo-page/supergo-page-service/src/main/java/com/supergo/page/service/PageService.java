package com.supergo.page.service;

import com.alibaba.fastjson.JSON;
import com.supergo.http.HttpResult;
import com.supergo.manager.feign.*;
import com.supergo.page.config.GoddsLock;
import com.supergo.page.util.Const;
import com.supergo.page.util.FileUtil;
import com.supergo.page.util.UUIDUtil;
import com.supergo.pojo.*;
import com.supergo.user.utils.CookieUtil;
import com.supergo.user.utils.JsonUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
    public HttpResult unloginAddOrderCart(HttpServletRequest request, HttpServletResponse response, int itemId, int num, int sellerId) {
        try {
            String clientId = "";
            Cookie[] cookies = request.getCookies();
            //如果cookie不为空，直接获取cookie值
            if(cookies != null) {
                Map<String, String> clientId1 = CookieUtil.readCookie(request, "clientId");
                clientId = clientId1.get("clientId");
            } else {
                clientId = UUIDUtil.getUUID2();
                //如果是第一次访问，创建cookie
                Cookie cookie = new Cookie("clientId", clientId);
                cookie.setMaxAge(3 * 24 * 3600);
                cookie.setPath("/");
                cookie.setDomain("supergo.com");
                response.addCookie(cookie);
            }
            System.out.println("clientId:  " + clientId);
            FileWriter fileWriter = new FileWriter(Const.pagePath + "unloginAddOrderCart" + Const.sufferHtml);
            Map<Object, Object> skuMap = apiOrderCartFeign.unloginAddOrderCart(itemId, clientId, num, sellerId);
            Context context = new Context();
            User userInfo = new User();
            String spec = (String) skuMap.get("spec");
            Map<String, String> stringStringMap = JsonUtils.jsonToMap(spec, String.class, String.class);
            //获取登入用户信息
            context.setVariable("userInfo",userInfo);
            context.setVariable("skuMap",skuMap);
            context.setVariable("spec",stringStringMap);
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
            Map<Object, Object> skuMap = apiOrderCartFeign.addOrderCart(itemId, num, sellerId);
            System.out.println(skuMap.toString());
            Context context = new Context();
            String spec = (String) skuMap.get("spec");
            Map<String, String> stringStringMap = JsonUtils.jsonToMap(spec, String.class, String.class);
            //获取登入用户信息
            context.setVariable("userInfo",userInfo);
            context.setVariable("bearerToken","Bearer " + token);
            context.setVariable("skuMap",skuMap);
            context.setVariable("spec",stringStringMap);
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
     * 用户登录情况下显示购物车数据
     * @return
     */
    public HttpResult showOrderCart(HttpServletRequest request) {
        User userInfo = new User();
        Claims claims = (Claims) request.getAttribute("userInfo");
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
        Context context = new Context();
        //获取登入用户信息
        context.setVariable("userInfo",userInfo);
        FileWriter fileWriter = null;
        String template = null;
        try {
            if(token != null) {
                context.setVariable("bearerToken","Bearer " + token);
                //获取购物车列表信息
                List<Map<Object,Object>> orderCartList = apiOrderCartFeign.getOrderCart();
                System.out.println(orderCartList);
                if(!orderCartList.isEmpty()) {
                    fileWriter = new FileWriter(Const.pagePath + "showOrderCart" + Const.sufferHtml);
                    List<Map<Object, Object>> orderCartList1 = parseOrderCartList(orderCartList);
                    context.setVariable("orderCartList",orderCartList1);
                    //获取地址信息
                    List<Provinces> provincesList = apiProvincesFeign.getProvincesList();
                    context.setVariable("provincesList",provincesList);
                    //每次创建模板前删除原来的模板
                    boolean b = FileUtil.deleteFile(Const.pagePath + "showOrderCart" + Const.sufferHtml);
                    System.out.println("删除文件成功！");
                    templateEngine.process("showOrderCart", context, fileWriter);
                    template = "showOrderCart";
                } else {
                    fileWriter = new FileWriter(Const.pagePath + "nullloginShowOrderCart" + Const.sufferHtml);
                    //每次创建模板前删除原来的模板
                    boolean b = FileUtil.deleteFile(Const.pagePath + "nullloginShowOrderCart" + Const.sufferHtml);
                    System.out.println("删除文件成功！");
                    templateEngine.process("nullloginShowOrderCart", context, fileWriter);
                    template = "nullloginShowOrderCart";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return HttpResult.ok(template);
    }

    /**
     * 解析购物车列表
     */
    public List<Map<Object,Object>> parseOrderCartList(List<Map<Object,Object>> orderCartList) {
        List<Map<Object,Object>> orderCartList1 = new ArrayList<>();
        for(Map<Object,Object> map : orderCartList) {
            String spec = (String) map.get("spec");
            Map<String, String> specMap = JsonUtils.jsonToMap(spec, String.class, String.class);
            map.put("spec",specMap);
            orderCartList1.add(map);
        }
        return orderCartList1;
    }

    /**
     * 用户未登录情况下，显示购物车
     * @param request
     * @return
     */
    public HttpResult unloginShowOrderCart(HttpServletRequest request,HttpServletResponse response) {
        String clientId = null;
        String template = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            //获取cookie信息
            Map<String, String> clientId1 = CookieUtil.readCookie(request, "clientId");
            clientId = clientId1.get("clientId");
        } else {
            clientId = UUIDUtil.getUUID2();
            System.out.println("clientId：   " + clientId);
            //如果是第一次访问，创建cookie
            Cookie cookie = new Cookie("clientId", clientId);
            cookie.setMaxAge(3 * 24 * 3600);
            cookie.setPath("/");
            cookie.setDomain("supergo.com");
            response.addCookie(cookie);
        }
        //如果用户
        if(clientId != null) {
            List<Map<Object, Object>> unloginOrderCart = apiOrderCartFeign.getUnloginOrderCart(clientId);
            if(unloginOrderCart.size() > 0) {
                //获取地址信息
                List<Provinces> provincesList = apiProvincesFeign.getProvincesList();
                List<Map<Object, Object>> orderCartList = parseOrderCartList(unloginOrderCart);
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(Const.pagePath + "showOrderCart" + Const.sufferHtml);
                    Context context = new Context();
                    User user = new User();
                    //获取登入用户信息
                    context.setVariable("userInfo",user);
                    context.setVariable("provincesList",provincesList);
                    context.setVariable("orderCartList",orderCartList);
                    //每次创建模板前删除原来的模板
                    boolean b = FileUtil.deleteFile(Const.pagePath + "showOrderCart" + Const.sufferHtml);
                    System.out.println("删除文件成功！");
                    templateEngine.process("showOrderCart", context, fileWriter);
                    template = "showOrderCart";
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                template = nullShowOrderCartTemplate();
            }
        } else {
            template = nullShowOrderCartTemplate();
        }
        return HttpResult.ok(template);
    }

    public String nullShowOrderCartTemplate() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(Const.pagePath + "nullShowOrderCart" + Const.sufferHtml);
            Context context = new Context();
            User user = new User();
            //获取登入用户信息
            context.setVariable("userInfo",user);
            //每次创建模板前删除原来的模板
            boolean b = FileUtil.deleteFile(Const.pagePath + "nullShowOrderCart" + Const.sufferHtml);
            System.out.println("删除文件成功！");
            templateEngine.process("nullShowOrderCart", context, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "nullShowOrderCart";
    }

    /**
     * 根据id获取库存信息
     * @param goodsId
     * @return
     */
    public List<Item> getItemByGoodsId(long goodsId) {
        // 查询库存列表
        List<Item> itemList = goodsFeign.getItemList(goodsId);
        return itemList;
    }
}
