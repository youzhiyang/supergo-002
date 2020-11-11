package com.supergo.manager.test;

import com.supergo.manager.ManagerApplication;
import com.supergo.manager.service.OrderCartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/11/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ManagerApplication.class)
public class OrderCartTest {

    @Autowired
    private OrderCartService orderCartService;

    @Test
    public void getOrderCart() {
        List<Map<Object, Object>> orderCart = orderCartService.getOrderCart();
        System.out.println(orderCart);
    }
}
