package com.supergo.search;

import com.supergo.search.entity.UserEntity;
import com.supergo.search.repository.UserRepostory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * 文档操作测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchApplication.class)
public class EsDocumentTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private UserRepostory userRepostory;

    /**
     * 添加、更新
     */
    @Test
    public void addDocument() {

        for(int i = 1;i <= 20;i++) {
            //创建一个文档对象
            UserEntity userEntity = new UserEntity();
            userEntity.setId(i+1);
            userEntity.setName("张三丰"+i);
            userEntity.setSex("男");
            userEntity.setAddress("山东东营");
            //把文档对象添加到索引库
            userRepostory.save(userEntity);
        }

        for(int i = 21;i <= 40;i++) {
            //创建一个文档对象
            UserEntity userEntity = new UserEntity();
            userEntity.setId(i + 1);
            userEntity.setName("赵敏" + i);
            userEntity.setSex("女");
            userEntity.setAddress("云南腾冲");
            //把文档对象添加到索引库
            userRepostory.save(userEntity);
        }

    }

    /**
     * 删除
     */
    @Test
    public void deleteById() {
        userRepostory.deleteById(1L);
    }

    /**
     * 更新
     */
    @Test
    public void testUpdateDocument() {
        //从索引库查询文档(Optional具备判空功能)
        Optional<UserEntity> optional = userRepostory.findById(1L);
        //更新文档内容
        UserEntity userEntity = optional.get();
        userEntity.setName("张无忌");
        //写入索引库
        userRepostory.save(userEntity);
    }
}
