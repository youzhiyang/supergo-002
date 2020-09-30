package com.supergo.search.repository;

import com.supergo.search.entity.UserEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 *索引库的操作类
 */
public interface UserRepostory extends ElasticsearchRepository<UserEntity,Long> {

    List<UserEntity> findByName(String name);
}
