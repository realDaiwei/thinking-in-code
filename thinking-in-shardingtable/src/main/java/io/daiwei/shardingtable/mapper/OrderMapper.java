package io.daiwei.shardingtable.mapper;

import io.daiwei.shardingtable.pojo.Order;

import java.util.List;

/**
 * Created by Daiwei on 2021/3/12
 */
public interface OrderMapper {

    void insertOrder(Order order);

    Order listOrders(Long id);

    List<Order> listOrders();

    void updateByOrderId(Long orderId);
}
