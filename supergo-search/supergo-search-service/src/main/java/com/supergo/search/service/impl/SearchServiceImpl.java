package com.supergo.search.service.impl;

import com.supergo.search.entity.GoodsEntity;
import com.supergo.search.entity.SearchResult;
import com.supergo.search.mapper.GoodsMapper;
import com.supergo.search.repository.GoodsRepository;
import com.supergo.search.service.SearchService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("searchServiceImpl")
public class SearchServiceImpl implements SearchService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public SearchResult search(String keyword, Map<String, String> filters, int page, int size) {
        //创建聚合查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder()
                //设置分页信息
                .withPageable(PageRequest.of(page, size))
                //添加聚合条件
                .addAggregation(AggregationBuilders.terms("category_aggs").field("cname3"))
                .addAggregation(AggregationBuilders.terms("brand_aggs").field("brand_name"))
                //设置高亮显示
                .withHighlightFields(new HighlightBuilder.Field("name").preTags("<em>").postTags("</em>"));

        //设置查询条件
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .should(QueryBuilders.multiMatchQuery(keyword, "goods_name"));
        if (filters != null && !filters.isEmpty()) {
            filters.keySet().forEach(key -> {
                boolQuery.filter(QueryBuilders.termQuery(key, filters.get(key)));
            });
        }

        queryBuilder.withQuery(boolQuery);
        //创建查询对象
        NativeSearchQuery build = queryBuilder.build();
        //执行查询
        AggregatedPage<GoodsEntity> result = elasticsearchTemplate.queryForPage(build, GoodsEntity.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                List<GoodsEntity> list = new ArrayList<>();
                SearchHits hits = searchResponse.getHits();
                SearchHit[] hits1 = hits.getHits();
                for (SearchHit hit : hits1) {
                    GoodsEntity goodsEntity = new GoodsEntity();
                    Map<String, Object> source = hit.getSource();
                    goodsEntity.setId((Long) source.get("id"));
                    goodsEntity.setGoods_name((String) source.get("goods_name"));
                    goodsEntity.setSeller_id((String) source.get("seller_id"));
                    goodsEntity.setNick_name((String) source.get("nick_name"));
                    goodsEntity.setBrand_id((Integer) source.get("brand_id"));
                    goodsEntity.setBrand_name((String) source.get("brand_name"));
                    goodsEntity.setCategory1_id((Integer) source.get("category1_id"));
                    goodsEntity.setCname1((String) source.get("cname1"));
                    goodsEntity.setCategory2_id((Integer) source.get("category2_id"));
                    goodsEntity.setCname2((String) source.get("cname2"));
                    goodsEntity.setCategory3_id((Integer) source.get("category3_id"));
                    goodsEntity.setCname3((String) source.get("cname3"));
                    goodsEntity.setSmall_pic((String) source.get("small_pic"));
                    goodsEntity.setPrice((Double) source.get("price"));
                    //取高亮结果
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    HighlightField goodsName = highlightFields.get("goods_name");
                    if (goodsName != null) {
                        String string = goodsName.getFragments()[0].string();
                        goodsEntity.setGoods_name(string);
                    }
                    list.add(goodsEntity);
                }

                return new AggregatedPageImpl<T>((List<T>) list, pageable, hits.totalHits, searchResponse.getAggregations());
            }
        });

        //获取查询结果
        List<GoodsEntity> content = result.getContent();
        SearchResult searchResult = new SearchResult();
        searchResult.setGoodsList(content);
        //取分类聚合结果
        Terms terms = (Terms) result.getAggregation("category_aggs");
        List<String> categoryList = terms.getBuckets().stream().map(e -> e.getKeyAsString()).collect(Collectors.toList());
        HashMap<Object, Object> catAggsMap = new HashMap<>();
        catAggsMap.put("name","品牌");
        catAggsMap.put("field","cname3");
        catAggsMap.put("content",categoryList);
        //取品牌聚合结果
        Terms terms1 = (Terms) result.getAggregation("brand_aggs");
        List<String> brandAggsList = terms.getBuckets().stream().map(e -> e.getKeyAsString()).collect(Collectors.toList());
        Map brandAggsMap = new HashMap();
        brandAggsMap.put("name","品牌");
        brandAggsMap.put("field", "brand_name");
        brandAggsMap.put("content", brandAggsList);
        List aggsList = new ArrayList();
        aggsList.add(brandAggsMap);
        aggsList.add(catAggsMap);

        //设置过滤条件
        searchResult.setAggs(aggsList);
        return searchResult;
    }
}
