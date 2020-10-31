package com.supergo.mapper;

import com.supergo.pojo.Item;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ItemMapper extends Mapper<Item> {

    /**
     * 根据itemId和sellerId查询数据库
     * @param itemId
     * @param sellerId
     * @return
     */
    @Select("SELECT\n" +
            "\ti.id AS item_id,\n" +
            "\ti.title AS title,\n" +
            "\tg.caption AS sku_info,\n" +
            "\ti.price,\n" +
            "\ti.image,\n" +
            "\tg.id AS goods_id,\n" +
            "\ti.seller_id,\n" +
            "\ti.spec\n" +
            "FROM\n" +
            "\ttb_goods g,\n" +
            "\ttb_item i\n" +
            "WHERE\n" +
            "\ti.goods_id = g.id\n" +
            "AND i.id = #{itemId}\n" +
            "AND i.seller_id = #{sellerId}")
    public Map<Object,Object> selectBySellerIdAndSkuId(@Param("itemId") int itemId, @Param("sellerId") int sellerId);

    /**
     * 根据商品id获取库存列表
     * @param goodsId
     */
    @Select("SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\ttb_item\n" +
            "WHERE\n" +
            "\tgoods_id = #{goodsId}")
    public List<Map<Object,Object>> getItemByGoodsId(@Param("goodsId")long goodsId);

}