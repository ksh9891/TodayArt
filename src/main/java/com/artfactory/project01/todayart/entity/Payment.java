package com.artfactory.project01.todayart.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="payment")
@DynamicInsert
@DynamicUpdate
public class Payment {
    @Id
    @Column(name="payment_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer paymentId;

    @Getter
    @Setter
    @Column(name = "order_id", updatable = false, nullable = false)
    private Integer orderId;

    @Getter
    @Setter
    @Column(name = "ordered_detail_id", updatable = false, nullable = false)
    private Integer orderedDetailId;

    @Getter
    @Setter
    @Column(name = "pay_dated")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date payDated;

    @Getter
    @Setter
    @Column(name = "pay_method")
    private String payMethod;

    @Getter
    @Setter
    @Column(name = "card_number")
    private String cardNumber;

    @Getter
    @Setter
    @Column(name = "order_detail_price")
    private int orderDetailPrice;

    @Getter
    @Setter
    @Column(name = "total_order_price")
    private int totalOrderPrice;

    @Getter
    @Setter
    @Column(name = "status")
    private String status;

    @Getter
    @Setter
    @Column(name = "pg_number")
    private Long pgNumber;

    @Getter
    @Setter
    @Column(name = "refund_comment")
    private String refundComment;

    public Payment setPayment(Payment payment){
        this.payMethod = payment.getPayMethod();
        this.cardNumber = payment.getCardNumber();
        this.totalOrderPrice = payment.getTotalOrderPrice();
        return this;
    }
}
