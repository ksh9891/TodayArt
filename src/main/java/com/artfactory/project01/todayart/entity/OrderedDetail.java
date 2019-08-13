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
public class OrderedDetail implements Serializable{

    @Id
    @Column(name="ordered_detail_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailId;

    @Column(name = "ordered_id")
    private int orderId;

    @Column(name = "product_id")
    private int productId;

    @Getter
    @Setter
    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "product_price")
    private Integer productPrice;

    @Getter
    @Setter
    @Column(name = "total_product_price")
    private Integer totalProductPrice;

    @Column(name = "total_price")
    private int totalPrice;




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

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public Integer getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Integer shippingFee) {
        this.shippingFee = shippingFee;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
