package com.artfactory.project01.todayart.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ordered")
@DynamicInsert
@DynamicUpdate
public class Ordered implements Serializable {
    @Id
    @Column(name = "ordered_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "order_dated")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;


    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "shipping_fee")
    private Integer shippingFee;

    @Column(name="isHidden")
    private int isHidden;

    @OneToMany(targetEntity=OrderedDetail.class, fetch = FetchType.EAGER)
    @JoinColumn(name="ordered_id")
    private List<OrderedDetail> orderDetails;

    /* OrderedDetail 과 join하기 */

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Integer shippingFee) {
        this.shippingFee = shippingFee;
    }

    public int getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(int hidden) {
        this.isHidden = isHidden;
    }

    public List<OrderedDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderedDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
