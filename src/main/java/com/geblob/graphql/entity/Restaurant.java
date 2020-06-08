package com.geblob.graphql.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(generator = "JDBC")
    private  Integer id;
    private String name;
    private String address;
}
