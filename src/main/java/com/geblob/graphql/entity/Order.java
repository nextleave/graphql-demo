package com.geblob.graphql.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private Integer consumerId;
    private Integer restaurantId;
    private List<OrderItem> orderItemList;
    private BigDecimal totalPrice;
    private String status;
}
