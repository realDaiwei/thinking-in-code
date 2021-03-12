package io.daiwei.shardingtable.service;

import io.daiwei.shardingtable.pojo.Order;

import java.util.List;

/**
 * Created by Daiwei on 2021/3/12
 */
public interface OrderService {

    void insertOrder(Order order);

    List<Order> selectOrder();
}
