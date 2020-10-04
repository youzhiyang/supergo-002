package com.supergo.manager.feign;

import com.supergo.http.HttpResult;
import com.supergo.pojo.Cities;
import com.supergo.pojo.Provinces;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(serviceId = "supergo-manager")
//@RequestMapping("/cities")   //不要在此注解上加载处理器映射器，会被处理器映射器扫描，进行误判
public interface ApiProvincesFeign {

    @RequestMapping("/provinces/getProvincesList")
    public List<Provinces> getProvincesList();
}
