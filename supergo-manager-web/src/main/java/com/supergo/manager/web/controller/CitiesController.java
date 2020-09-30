package com.supergo.manager.web.controller;

import com.supergo.http.HttpResult;
import com.supergo.manager.feign.ApiCitiesFeign;
import com.supergo.pojo.Cities;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//类说明
@Api(value = "城市控制器", protocols = "http", description = "城市控制器")
@RestController
@RequestMapping("/cities")
public class CitiesController {

    @Autowired
    private ApiCitiesFeign apiCitiesFeign;

    @GetMapping("/{citiesId}")
    public HttpResult getCitiesById(@PathVariable Long citiesId) {
        Cities cities = apiCitiesFeign.getCitiesById(citiesId);
        return HttpResult.ok(cities);
    }

    //api方法说明
    @ApiOperation(value = "分页查询",notes="接收分页参数page，size")
    @PostMapping("/getCitiesList/{page}/{size}")
    @ApiImplicitParams({   //参数说明
            @ApiImplicitParam(paramType = "path",name="page",value = "显示页码",required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "path",name="size",value = "一共多少条数据",required = true,dataType = "int")
    })
    @ApiResponses({   //响应说明
            @ApiResponse(code = 200, message = "查询品牌成功"),
            @ApiResponse(code = 500, message = "查询品牌失败,后台服务出现异常"),
            @ApiResponse(code = 401, message = "代表此操作无权限访问"),
            @ApiResponse(code = 400, message = "表示请求参数错误"),
    })
    public HttpResult getCitiesList(@PathVariable int page, @PathVariable int size, @RequestBody(required = false) Cities cities) {
        HttpResult citiesList = apiCitiesFeign.getCitiesList(page, size, cities);
        return citiesList;
    }
}
