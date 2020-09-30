package com.supergo.page.feign;

import com.supergo.http.HttpResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@FeignClient(serviceId = "supergo-page")
public interface ApiPageFeign {

    @RequestMapping("/html/build/{goodsId}")
    public HttpResult buildHtml(@PathVariable Long goodsId, HttpServletRequest request);
}
