package com.supergo.manager.feign;

import com.supergo.pojo.Cities;
import com.supergo.http.HttpResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(serviceId = "supergo-manager")
//@RequestMapping("/cities")   //不要在此注解上加载处理器映射器，会被处理器映射器扫描，进行误判
public interface ApiCitiesFeign {

    @RequestMapping("/cities/{citiesId}")
    public Cities getCitiesById(@PathVariable long citiesId);

    @RequestMapping("/cities/getCitiesList/{page}/{size}")
    public HttpResult getCitiesList(@PathVariable int page, @PathVariable int size, @RequestBody(required = false) Cities cities);
}
