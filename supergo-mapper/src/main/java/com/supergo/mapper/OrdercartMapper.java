package com.supergo.mapper;

import com.supergo.pojo.Ordercart;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

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
}