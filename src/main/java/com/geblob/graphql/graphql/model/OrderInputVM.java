package com.geblob.graphql.graphql.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class OrderInputVM {
    private Integer consumerId;
    private Integer restaurantId;
    private BigDecimal totalPrice;

    public static OrderInputVM fromMap(Map<String, Object> map) {
        OrderInputVM orderInputVM = new OrderInputVM();
        orderInputVM.setConsumerId(map.containsKey("consumerId") ? Integer.parseInt(map.get("consumerId").toString()) : null);
        orderInputVM.setRestaurantId(map.containsKey("restaurantId") ? Integer.parseInt(map.get("restaurantId").toString()) : null);
        orderInputVM.setTotalPrice(map.containsKey("totalPrice") ? new BigDecimal(map.get("totalPrice").toString()) : null);
        return orderInputVM;
    }
}
