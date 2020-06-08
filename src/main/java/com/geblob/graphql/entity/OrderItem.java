package com.geblob.graphql.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private String goodsName;
    private Integer orderId;
    private Integer count;
    private BigDecimal price;
}
