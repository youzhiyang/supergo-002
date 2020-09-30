package com.supergo.mapper;

import com.supergo.pojo.Item;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface ItemMapper extends Mapper<Item> {

    /**
     * 根据itemId和sellerId查询数据库
     * @param itemId
     * @param sellerId
     * @return
     */
    @Select("SELECT\n" +
            "\tg.default_item_id as item_id,\n" +
            "\ti.title as title,\n" +
            "\tg.caption as sku_info,\n" +
            "\tg.price,\n" +
            "\ti.num,\n" +
            "\ti.image,\n" +
            "\tg.id as goods_id,\n" +
            "\tg.seller_id\n" +
            "FROM\n" +
            "\ttb_goods g,\n" +
            "\ttb_item i\n" +
            "WHERE\n" +
            "\tg.default_item_id = i.id\n" +
            "AND i.id = #{itemId}\n" +
            "AND g.seller_id = #{sellerId}")
    public Map<String,String> selectBySellerIdAndSkuId(@Param("itemId") int itemId, @Param("sellerId") String sellerId);
}