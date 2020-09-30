package com.supergo.manager.feign;

import com.supergo.pojo.Itemcat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(serviceId = "supergo-manager")
public interface ApiItemCatFeign {

    @RequestMapping("/itemcat/category")
    public List<Itemcat> getItenCatList();
    @RequestMapping("/itemcat/category/{categoryId}")
    public Itemcat getItemCatById(@PathVariable("categoryId") long categoryId);
}
