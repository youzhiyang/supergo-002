package com.supergo.search.service;

import com.supergo.search.entity.SearchResult;

import java.util.Map;

public interface SearchService {

    public SearchResult search(String keyword, Map<String,String> filters, int page,int size);
}
