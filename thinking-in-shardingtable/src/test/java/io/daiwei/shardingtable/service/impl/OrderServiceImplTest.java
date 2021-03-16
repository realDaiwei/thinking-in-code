package io.daiwei.shardingtable.service.impl;

import io.daiwei.shardingtable.pojo.Order;
import io.daiwei.shardingtable.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

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

    @Test
    void select() {
        Order order = orderService.selectOrder(20L);
        System.out.println(order);
    }

    @Test
    void list() {
        List<Order> orders = orderService.listOrder();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    void update() {
        orderService.updateByOrderId(3L);
    }
}