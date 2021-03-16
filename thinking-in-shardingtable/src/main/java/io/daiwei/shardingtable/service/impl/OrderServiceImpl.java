package io.daiwei.shardingtable.service.impl;

import io.daiwei.shardingtable.mapper.OrderMapper;
import io.daiwei.shardingtable.pojo.Order;
import io.daiwei.shardingtable.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Daiwei on 2021/3/12
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public void insertOrder(Order order) {
        orderMapper.insertOrder(order);
    }

    @Override
    public Order selectOrder(Long orderId) {
        return orderMapper.listOrders(orderId);
    }

    @Override
    public List<Order> listOrder() {
        return orderMapper.listOrders();
    }

    @Override
    public void updateByOrderId(Long id) {
        orderMapper.updateByOrderId(id);
    }
}
