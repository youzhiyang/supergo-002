package com.supergo.page.service;

import com.supergo.manager.feign.ApiGoodsFeign;
import com.supergo.manager.feign.ApiOrderCartFeign;
import com.supergo.page.util.Const;
import com.supergo.page.util.FileUtil;
import com.supergo.pojo.Ordercart;
import com.supergo.pojo.User;
import com.supergo.user.utils.CookieUtil;
import com.supergo.user.utils.JsonUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
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
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private TemplateEngine templateEngine;

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

    /**
     * 同步购物车数据
     */
    public void synchronizeOrderCart(HttpServletRequest request) {
        String clientId = null;
        Cookie[] cookies = request.getCookies();
        for(int i = 0;i < cookies.length;i++) {
            System.out.println(cookies[i]);
        }
        if(cookies != null) {
            //获取cookie信息
            Map<String, String> clientId1 = CookieUtil.readCookie(request, "clientId");
            clientId = clientId1.get("clientId");
            System.out.println(clientId);
        }
        User userInfo = new User();
        Claims claims = (Claims) request.getAttribute("userInfo");
        String userId = null;
        if(claims != null) {
            userId = claims.getId();
        }
        apiOrderCartFeign.synchronizeOrderCart(clientId,Integer.parseInt(userId));
    }

    /**
     * 结算页跳转
     */
    public void goAccount(HttpServletRequest request) {
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

            FileWriter fileWriter = new FileWriter(Const.pagePath + "orderInfo" + Const.sufferHtml);
            //Map<Object, Object> skuMap = apiOrderCartFeign.addOrderCart(itemId, num, sellerId);
            //System.out.println(skuMap.toString());
            Context context = new Context();
            //String spec = (String) skuMap.get("spec");
            //Map<String, String> stringStringMap = JsonUtils.jsonToMap(spec, String.class, String.class);
            //获取登入用户信息
            context.setVariable("userInfo",userInfo);
            context.setVariable("bearerToken","Bearer " + token);
            //context.setVariable("skuMap",skuMap);
            //context.setVariable("spec",stringStringMap);
            //每次创建模板前删除原来的模板
            boolean b = FileUtil.deleteFile(Const.pagePath + "orderInfo" + Const.sufferHtml);
            System.out.println("删除文件成功！");
            templateEngine.process("orderInfo", context, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
