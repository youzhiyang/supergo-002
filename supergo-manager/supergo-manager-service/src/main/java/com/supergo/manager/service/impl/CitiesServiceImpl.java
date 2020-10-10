package com.supergo.manager.service.impl;

import com.supergo.manager.service.CitiesService;
import com.supergo.mapper.CitiesMapper;
import com.supergo.mapper.ItemMapper;
import com.supergo.pojo.Cities;
import com.supergo.pojo.Item;
import com.supergo.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CitiesServiceImpl extends BaseServiceImpl<Cities> implements CitiesService {

}
