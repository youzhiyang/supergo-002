package com.supergo.page.util;

/**
 * Created by Administrator on 2020/8/11 0011.
 */
public class Const {

    //页面刷新接口，字段（拦截器中判断是否是页面刷新请求）
    public static String htmlFlash = "html/build";
    //html页面保存地址
    public static String pagePath = "E:\\supergo\\supergo\\supergo\\supergo-002\\supergo-page\\supergo-page-service\\src\\main\\resources\\goods\\";
    //html页面保存后缀
    public static String sufferHtml = ".html";
    //用户未登录添加购物车html页面名称(对该请求放行)
    public static String unloginAddOrderCart = "goods/unloginAddOrderCart";
    //获取商品库存列表
    public static String getItemByGoodsId = "goods/getItemByGoodsId";

}
