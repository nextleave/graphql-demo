package com.geblob.graphql.graphql.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderItemInputVM {
    private String goodsName;
    private Integer count;
    private BigDecimal price;
    private List<OrderItemInputVM> orderItems;
}
