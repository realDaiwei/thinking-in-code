package io.daiwei.shardingtable.service.impl;

import io.daiwei.shardingtable.mapper.OrderMapper;
import io.daiwei.shardingtable.pojo.Order;
import io.daiwei.shardingtable.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Order> selectOrder() {
        return null;
    }
}
