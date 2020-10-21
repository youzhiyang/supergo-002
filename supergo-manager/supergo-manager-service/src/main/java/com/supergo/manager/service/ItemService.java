package com.supergo.manager.service;

import com.supergo.pojo.Item;
import com.supergo.service.base.BaseService;

import java.util.List;
import java.util.Map;

public interface ItemService extends BaseService<Item> {
    public List<Item> skuList(Long goodsId);
    public int getItemStock(long itemId);
}
