package com.supergo.search.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.lang.annotation.Documented;

//一个Entity对应一个Document对对象
//indexName:索引库的名称
//type:索引库中的type
@Document(indexName = "test01", type = "User")
public class UserEntity {
    //主键字段上使用@id字段标注
    @Id
    //字段的定义 type：数据类型 store： 是否存储 index：是否索引 analyzer：使用的是什么分词器
    @Field(type = FieldType.Long, store = true)
    private long id;
    @Field(type = FieldType.text, store = true, analyzer = "ik_smart")
    private String name;
    //keyword:不分词
    @Field(type = FieldType.keyword, store = true)
    private String sex;
    @Field(type = FieldType.text, store = true, analyzer = "ik_max_word")
    private String address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
