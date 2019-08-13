package com.artfactory.project01.todayart.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="cart")
@DynamicUpdate
@DynamicInsert
public class Cart {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="cart_id", nullable = false, updatable = false)
    private Integer cartId;

    @Getter
    @Setter
    @Column(name ="member_id", nullable = false, updatable = false)
    private int memberId;

/*
    @Getter
    @Setter
    @Column(name="product_id")
    private int productId;
*/
    @Getter
    @Setter
    @ManyToOne(fetch=FetchType.EAGER, targetEntity = Product.class)
    @JoinColumn(name ="product_id", nullable = false, updatable = false)
    private Product product;

    @Getter
    @Setter
    @Column(name="product_price")
    private int productPrice;

    @Getter
    @Setter
    @Column(name="product_size")
    private String productSize;

    @Getter
    @Setter
    @Column(name="quantity")
    private int quantity;

    @Getter
    @Setter
    @Column(name="shipping_fee")
    private int shippingFee;

    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name="enroll_dated")
    private Date enrollDated;

    @Getter
    @Setter
    @Column(name="is_deleted")
    private int isDeleted;

    @Getter
    @Setter
    @Column(name="is_stock")
    private int isStock;


}
