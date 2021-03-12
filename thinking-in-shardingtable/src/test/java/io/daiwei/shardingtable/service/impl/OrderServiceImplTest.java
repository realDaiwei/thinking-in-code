package io.daiwei.shardingtable.service.impl;

import io.daiwei.shardingtable.pojo.Order;
import io.daiwei.shardingtable.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Daiwei on 2021/3/12
 */
@SpringBootTest
class OrderServiceImplTest {

    @Resource
    private OrderService orderService;

    @Test
    void insertOrder() {
        for (int i = 0; i < 100; i++) {
            Order order = new Order(Long.valueOf(i), Long.valueOf(i));
            orderService.insertOrder(order);
        }
    }
}