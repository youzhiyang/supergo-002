package com.supergo.search.entity;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {
    private List<GoodsEntity> goodsList;
    private List<?> aggs;
    public List<GoodsEntity> getGoodsList() {
        return goodsList;
    }
    public void setGoodsList(List<GoodsEntity> goodsList) {
        this.goodsList = goodsList;
    }
    public List<?> getAggs() {
        return aggs;
    }
    public void setAggs(List<?> aggs) {
        this.aggs = aggs;
    }
}

