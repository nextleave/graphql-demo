package com.geblob.graphql.resolver;

import com.geblob.graphql.entity.Order;
import com.geblob.graphql.graphql.model.OrderInputVM;
import com.geblob.graphql.service.OrderService;
import com.geblob.graphql.service.RestaurantService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DataFetchers {
    @Autowired
    OrderService orderService;
    @Autowired
    RestaurantService restaurantService;

    public DataFetcher getOrderByIdFetcher() {
        return dataFetchingEnvironment -> {
            String orderId = dataFetchingEnvironment.getArgument("id");
            return orderService.get(Integer.parseInt(orderId));
        };
    }

    public DataFetcher getRestaurantByIdFetcher() {
        return dataFetchingEnvironment -> {
            Order order = dataFetchingEnvironment.getSource();
            return restaurantService.get(order.getRestaurantId());
        };
    }

    public DataFetcher getOrderOfConsumerFetcher() {
        return dataFetchingEnvironment -> {
            String consumerId = dataFetchingEnvironment.getArgument("consumerId");
            return orderService.getOrdersOfConsumer(Integer.parseInt(consumerId));
        };
    }

    public DataFetcher placeOrderFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, Object> orderInputMap = dataFetchingEnvironment.getArgument("orderInput");
            OrderInputVM orderInputVM = OrderInputVM.fromMap(orderInputMap);
            return orderService.placeOrder(orderInputVM);
        };
    }

    public DataFetcher updateOrderFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, Object> orderInputMap = dataFetchingEnvironment.getArgument("orderInput");
            OrderInputVM orderInputVM = OrderInputVM.fromMap(orderInputMap);
            return orderService.updateOrder(orderInputVM);
        };
    }
}
