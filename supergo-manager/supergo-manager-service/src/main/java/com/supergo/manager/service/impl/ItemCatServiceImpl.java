package com.supergo.manager.service.impl;

import com.supergo.mapper.ItemcatMapper;
import com.supergo.pojo.Itemcat;
import com.supergo.service.base.impl.BaseServiceImpl;
import com.supergo.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ItemCatServiceImpl extends BaseServiceImpl<Itemcat> implements ItemCatService {
    @Autowired
    private ItemcatMapper itemcatMapper;
    //@Autowired
    //private StringRedisTemplate redisTemplate;

    @Override
    public List<Itemcat> getItemCatList() {
        List<Itemcat> list = getCategroyList(0);
        return list;
    }

    private List<Itemcat> getCategroyList(long parentId) {

        Example example = new Example(Itemcat.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", parentId);
        List<Itemcat> list = itemcatMapper.selectByExample(example);
        if (list==null || list.size() ==0) {
            return null;
        }
        list.forEach(itemcat -> itemcat.setChildren(getCategroyList(itemcat.getId())));
        return list;
    }
}
