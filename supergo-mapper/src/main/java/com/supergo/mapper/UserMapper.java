package com.supergo.mapper;

import com.supergo.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

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