package com.artfactory.project01.todayart.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ordered_detail")
@DynamicInsert
@Getter
@Setter
public class OrderedDetail implements Serializable {
    @Id
    @Column(name="ordered_detail_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderDetailId;

    @Column(name = "ordered_id")
    private Integer orderId;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Integer productId;

    @Column(name = "cart_id")
    private Integer cartId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "product_price")
    private Integer productPrice;

    @Column(name = "total_product_price")
    private Integer totalProductPrice;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name ="product_name")
    private String productName;

    @Column(name ="product_size")
    private String productSize;

    @Column(name ="shipping_fee")
    private Integer shippingFee;

    @Column(name ="tracking_number")
    private String trackingNumber;

    @Column(name = "status")
    private String status;

    @OneToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
}
