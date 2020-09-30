package com.supergo.sso.mapper;

import com.supergo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/7/7.
 */
//@Repository
public interface UserMapper {

    @Select("SELECT\n" +
            "\ttu.*, tr.role_name\n" +
            "FROM\n" +
            "\ttb_user tu,\n" +
            "\ttb_role tr\n" +
            "WHERE\n" +
            "\ttu.role_id = tr.id\n" +
            "AND tu.username = #{username}")
    public List<User> getUserByUserName(User user);
}
