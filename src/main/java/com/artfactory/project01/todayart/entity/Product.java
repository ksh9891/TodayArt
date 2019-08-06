package com.artfactory.project01.todayart.entity;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "product")
@DynamicUpdate
@DynamicInsert
public class Product {

    @Id
    @Column(columnDefinition = "int", name = "product_id", nullable=false, updatable = false)
    private Integer product_id;

    private String product_name;

    private Integer artist_id;

    private String product_size;

    private Integer product_price;

    private Integer thumbnail_id;

    private Integer is_delete;

    private Date delete_dated;

    private Date enroll_dated;

    private Integer category;

    private Integer is_sold_out;

    private Integer remain;

    private Integer count_cart;

    private Integer count_wishlist;

    private Integer shipping_fee;















}


