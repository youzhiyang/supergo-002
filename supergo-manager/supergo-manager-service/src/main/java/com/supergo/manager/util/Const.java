package com.supergo.manager.util;

/**
 * 接口字段放行实例类
 */
public class Const {

    //页面未登入添加购物车请求放行
    public static String unloginAddOrderCart = "orderCart/unloginAddOrderCart";
    //获取商品库存列表
    public static String getItemByGoodsId = "goods/getItemByGoodsId";
    //查询购物车列表
    public static String getOrderCart = "orderCart/getOrderCart";
    //更新购物车
    public static String updateOrderCart = "orderCart/updateOrderCart";
    //获取购物车列表信息
    public static String getOrderCartList = "orderCart/getOrderCartList";
    //获取剩余库存
    public static String getItemStock = "orderCart/getItemStock";
    //根据主键查询购物车
    public static String selectByPrimaryKey = "orderCart/selectByPrimaryKey";

}
