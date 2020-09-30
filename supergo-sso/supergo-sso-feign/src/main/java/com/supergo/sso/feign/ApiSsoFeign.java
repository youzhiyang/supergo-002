package com.supergo.sso.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "supergo-sso")
public interface ApiSsoFeign {

    /**
     * 判断key是否存在
     */
    @RequestMapping("/redis/hasKey/{key}")
    public Boolean hasKey(@PathVariable("key") String key);

}
