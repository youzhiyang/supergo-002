package com.supergo.search;

import com.supergo.search.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 索引操作测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchApplication.class)
public class ElasticSearchTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    //创建索引库
    @Test
    public void createIndex() {
        //创建索引库
        boolean index = elasticsearchTemplate.createIndex(UserEntity.class);
    }

    //删除索引库
    @Test
    public void deleteIndex() {
        //elasticsearchTemplate.deleteIndex(UserEntity.class);
        elasticsearchTemplate.deleteIndex("hello3");
    }

    //设置mapping
    @Test
    public void putMapping() {
        elasticsearchTemplate.putMapping(UserEntity.class);
    }

}
