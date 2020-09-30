package com.supergo.search.controller;

import com.supergo.http.HttpResult;
import com.supergo.search.entity.SearchResult;
import com.supergo.search.service.SearchGoodsService;
import com.supergo.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 商品搜索控制类
 */
@RestController
public class SearchController {

    @Autowired
    private SearchGoodsService searchGoodsServiceImpl;
    @Autowired
    private SearchService searchServiceImpl;

    /**
     * 导入商品到索引库
     * @return
     */
    @RequestMapping("/goods/import")
    public HttpResult goodsImport() {
        //开启线程导入数据
        new Thread(()->searchGoodsServiceImpl.importGoods()).start();
        searchGoodsServiceImpl.importGoods();
        return HttpResult.ok();
    }

    /**
     * 去索引库搜索商品
     * @param keyword
     * @param filters
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/goods/search")
    public SearchResult search(@RequestParam(required = true) String keyword,
                             @RequestParam(required = false,value = "ev") String filters,
                             @RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "50") int size) {
        //filter参数的形式：ev=brand_name-小米/category3_id-255丶

        Map<String, String> filterMap = null;
        try {
            if(StringUtils.isNotEmpty(filters)) {
                String[] split = filters.split("\\|");
                filterMap = Stream.of(split).collect(Collectors.toMap(e -> e.split("-")[0], e -> e.split("-")[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SearchResult searchResult = searchServiceImpl.search(keyword, filterMap, page, size);
        return searchResult;
    }
}
