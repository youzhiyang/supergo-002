package com.supergo.search.mapper;

import com.supergo.search.entity.GoodsEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 复杂查询
 */
@Repository
public interface GoodsMapper {

    @Select("SELECT\n" +
            "\ta.id,\n" +
            "\ta.goods_name,\n" +
            "\ta.seller_id,\n" +
            "\tb.nick_name,\n" +
            "\ta.brand_id,\n" +
            "\tc. NAME brand_name,\n" +
            "\ta.category1_id,\n" +
            "\td. NAME cname1,\n" +
            "\ta.category2_id,\n" +
            "\te. NAME cname2,\n" +
            "\ta.category3_id,\n" +
            "\tf. NAME cname3,\n" +
            "\ta.small_pic,\n" +
            "\ta.price\n" +
            "FROM\n" +
            "\ttb_goods a\n" +
            "LEFT JOIN tb_seller b ON a.seller_id = b.seller_id\n" +
            "LEFT JOIN tb_brand c ON a.brand_id = c.id\n" +
            "LEFT JOIN tb_item_cat d ON a.category1_id = d.id\n" +
            "LEFT JOIN tb_item_cat e ON a.category2_id = e.id\n" +
            "LEFT JOIN tb_item_cat f ON a.category3_id = f.id\n" +
            "WHERE\n" +
            "\ta.is_delete = 0\n" +
            "AND a.is_marketable = 1")
    public List<GoodsEntity> getGoodsList();
}
