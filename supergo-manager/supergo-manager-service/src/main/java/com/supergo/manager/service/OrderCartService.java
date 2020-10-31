package com.supergo.manager.service;

import com.supergo.pojo.Ordercart;
import com.supergo.service.base.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 购物车Service
 */
public interface OrderCartService extends BaseService<Ordercart> {

    public Map<Object, Object> addOrderCart(HttpServletRequest httpRequest, int itemId , int num,int sellerId);

    public Map<Object, Object> unloginAddOrderCart(int itemId, int num, int sellerId, String clientId);

    public List<Map<Object,Object>> getOrderCart();

    public void updateOrderCart(int id,int num);

    public Ordercart selectByPrimaryKey(long id);
}
