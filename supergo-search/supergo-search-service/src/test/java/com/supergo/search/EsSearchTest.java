package com.supergo.search;

import com.supergo.search.entity.UserEntity;
import com.supergo.search.repository.UserRepostory;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 查询测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchApplication.class)
public class EsSearchTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private UserRepostory userRepostory;

    //根据id进行查询
    @Test
    public void testSearchById() {
        Optional<UserEntity> optional = userRepostory.findById(12l);
        UserEntity userEntity = optional.get();
        System.out.println(userEntity.toString());
    }

    //查询全部
    @Test
    public void testFindAll() {
        //查询全部
        //Iterable<UserEntity> all = userRepostory.findAll();
        //分页查询
        //Iterable<UserEntity> all = userRepostory.findAll(PageRequest.of(1,5));
        //查询排序
        Iterable<UserEntity> all = userRepostory.findAll(Sort.by(Sort.Order.asc("sex")));
        all.forEach(e->System.out.println(e));
    }

    //自定义基础查询
    @Test
    public void testFindByName(){
        List<UserEntity> nameList = userRepostory.findByName("张三丰");
        nameList.forEach(e->System.out.println(e));
    }

    //term查询
    @Test
    public void testTermQuery(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termQuery("name","赵敏"))
                .build();
        List<UserEntity> userEntities = elasticsearchTemplate.queryForList(searchQuery, UserEntity.class);
        userEntities.forEach(u->System.out.println(u));
    }

    //queryString查询
    @Test
    public void testQueryString(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("赵敏是一个好姑娘").defaultField("name"))
                .build();
        List<UserEntity> userEntities = elasticsearchTemplate.queryForList(searchQuery, UserEntity.class);
        userEntities.forEach(u->System.out.println(u));
    }

    //queryString查询
    @Test
    public void testMultiMatchString(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery("赵敏是一个好姑娘","name","address","sex"))
                .build();
        List<UserEntity> userEntities = elasticsearchTemplate.queryForList(searchQuery, UserEntity.class);
        userEntities.forEach(u->System.out.println(u));
    }

    //bool查询
    @Test
    public void testBoolString(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery()
                        .must(QueryBuilders.termQuery("name","张三丰"))
                        .should(QueryBuilders.termQuery("sex","男"))
                        //.filter(QueryBuilders.termQuery("name","3"))
                )
                .withFilter(QueryBuilders.termQuery("name","3"))
                .build();
        List<UserEntity> userEntities = elasticsearchTemplate.queryForList(searchQuery, UserEntity.class);
        userEntities.forEach(u->System.out.println(u));
    }

    //聚合查询
    @Test
    public void testAggregation(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery())
                //聚合条件
                .addAggregation(AggregationBuilders.terms("sex_count").field("sex"))
                .withPageable(PageRequest.of(1,5))
                .build();
        //List<UserEntity> userEntities = elasticsearchTemplate.queryForList(searchQuery, UserEntity.class);
        //userEntities.forEach(u->System.out.println(u));
        AggregatedPage<UserEntity> userEntities = elasticsearchTemplate.queryForPage(searchQuery, UserEntity.class);
        List<UserEntity> content = userEntities.getContent();
        userEntities.forEach(u->System.out.println(u));
        //聚合结果
        Terms terms = (Terms) userEntities.getAggregation("sex_count");
        terms.getBuckets()
                .forEach(b->{
                    System.out.println(b.getKey());
                    System.out.println(b.getDocCount());
                });
    }

    //测试高亮
    @Test
    public void testHeightLight() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("张三丰是一代武侠宗师").defaultField("name"))
                .withHighlightFields(new HighlightBuilder.Field("name").preTags("<em>").postTags("</em>"))
                .withPageable(PageRequest.of(1,5))
                .build();
        //执行查询
        AggregatedPage<UserEntity> result = elasticsearchTemplate.queryForPage(searchQuery, UserEntity.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                //取查询结果
                SearchHits hits = searchResponse.getHits();
                SearchHit[] hits1 = hits.getHits();
                List<Object> list = new ArrayList<>();
                for (SearchHit hit : hits1) {
                    //把查询结果封装成userEntity对象
                    Map<String, Object> source = hit.getSource();
                    UserEntity userEntity = new UserEntity();
                    userEntity.setId(((Integer) source.get("id")).longValue());
                    //取高亮结果
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    HighlightField name = highlightFields.get("name");
                    Text fragments = name.getFragments()[0];
                    String hlName = fragments.string();
                    userEntity.setName(hlName);
                    userEntity.setAddress((String) source.get("sex"));
                    userEntity.setAddress((String) source.get("address"));
                    //把结果放进list里
                    list.add(userEntity);
                }
                //创建AggregatedPage对象，其中包含所有结果，并返回
                return new AggregatedPageImpl<T>((List<T>) list, pageable, hits.totalHits, searchResponse.getAggregations());
            }
        });

        //获取查询结果
        List<UserEntity> content = result.getContent();
        content.forEach(u->System.out.println(u));
    }
}
