package com.supergo.mapper;

import com.supergo.pojo.Ordercart;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface OrdercartMapper extends Mapper<Ordercart> {

    /**
     * 判断商品是否存在
     * @param itemId
     * @param userId
     * @param sellerId
     * @return
     */
    @Select("SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\torder_cart\n" +
            "WHERE\n" +
            "\tuser_id = #{userId}\n" +
            "AND item_id = #{itemId}\n" +
            "AND seller_id = #{sellerId}")
    public Ordercart selectGoodsIsExist(@Param("itemId")int itemId,@Param("userId")int userId,@Param("sellerId")int sellerId);

    /**
     * 查询购物车数据
     * @return
     */
    @Select("SELECT\n" +
            "\to.*, i.spec\n" +
            "FROM\n" +
            "\torder_cart o,\n" +
            "\ttb_item i\n" +
            "WHERE\n" +
            "\to.item_id = i.id")
    public List<Map<Object,Object>> getOrderCart();
}