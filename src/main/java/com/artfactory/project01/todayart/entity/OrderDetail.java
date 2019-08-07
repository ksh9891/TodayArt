package com.artfactory.project01.todayart.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="order_detail")
@DynamicInsert
public class OrderDetail implements Serializable{

    @Embeddable
    public static class OrderDetailId implements Serializable {
        @Column(name = "order_id")
        private int orderId;

        @Column(name = "product_id")
        private int productId;

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
    }

    @EmbeddedId
    private OrderDetailId orderDetailId;

    @Column(name = "product_price")
    private Integer productPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "status")
    private String status;

    public OrderDetailId getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderId, int productId) {
        orderDetailId = new OrderDetailId();
        orderDetailId.setOrderId(orderId);
        orderDetailId.setProductId(productId);
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
