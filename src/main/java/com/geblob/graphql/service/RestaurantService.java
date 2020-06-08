package com.geblob.graphql.service;

import com.geblob.graphql.dao.ConsumerMapper;
import com.geblob.graphql.dao.OrderItemMapper;
import com.geblob.graphql.dao.OrderMapper;
import com.geblob.graphql.dao.RestaurantMapper;
import com.geblob.graphql.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestaurantMapper restaurantMapper;
    @Autowired
    private ConsumerMapper consumerMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    public Restaurant get(Integer id) {
        return restaurantMapper.selectByPrimaryKey(id);
    }

}
