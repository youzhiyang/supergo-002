package com.supergo.manager.service;

import com.supergo.pojo.Ordercart;
import com.supergo.service.base.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 购物车Service
 */
public interface OrderCartService extends BaseService<Ordercart> {

    public Map<Object, Object> addOrderCart(HttpServletRequest httpRequest, int itemId , int num,String sellerId);

    public Map<Object, Object> unloginAddOrderCart(int itemId, int num, int sellerId, String clientId);
}
