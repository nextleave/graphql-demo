package com.geblob.graphql.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "consumer")
public class Consumer {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private String name;
    private String mobile;
    private String sex;
    private String address;
}
