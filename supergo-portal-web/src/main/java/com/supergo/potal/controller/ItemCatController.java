package com.supergo.potal.controller;

import com.supergo.http.HttpResult;
import com.supergo.manager.feign.ApiItemCatFeign;
import com.supergo.pojo.Itemcat;
import com.supergo.user.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemCatController {

    @Autowired
    private ApiItemCatFeign apiItemCatFeign;
    @Autowired   //redis缓存方法
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/category/categorys")
    public HttpResult getItemCatList() {
        //查询商品分类之前先查询缓存
        String json = stringRedisTemplate.opsForValue().get("portal-itemcats");
        //如果缓存中有数据，直接返回
        if(!StringUtils.isEmpty(json)) {
            return HttpResult.ok(json);
        }
        //如果缓存中没有数据，查询数据
        List<Itemcat> itenCatList = apiItemCatFeign.getItenCatList();
        //把数据放入缓存中
        stringRedisTemplate.opsForValue().set("portal-itemcats", JsonUtils.objectToJson(itenCatList));
        //返回结果
        return HttpResult.ok(itenCatList);
    }
}
