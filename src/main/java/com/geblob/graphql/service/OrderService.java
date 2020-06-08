package com.geblob.graphql.service;

import com.geblob.graphql.dao.ConsumerMapper;
import com.geblob.graphql.dao.OrderItemMapper;
import com.geblob.graphql.dao.OrderMapper;
import com.geblob.graphql.dao.RestaurantMapper;
import com.geblob.graphql.entity.Order;
import com.geblob.graphql.graphql.model.OrderInputVM;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestaurantMapper restaurantMapper;
    @Autowired
    private ConsumerMapper consumerMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;


    public Order get(Integer orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    public List<Order> getOrdersOfConsumer(Integer consumerId) {
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("consumerId", consumerId);
        return orderMapper.selectByExample(example);
    }

    public Order placeOrder(OrderInputVM orderInputVM) {
        Order order = new Order();
        BeanUtils.copyProperties(orderInputVM, order);
        orderMapper.insert(order);
        return order;
    }

    public Order updateOrder(OrderInputVM orderInputVM) {
        Order order = new Order();
        BeanUtils.copyProperties(orderInputVM, order);
        orderMapper.updateByPrimaryKeySelective(order);
        return order;
    }
}
