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
    private int orderId;

    @Getter
    @Setter
    @Column(name = "product_id", updatable = false, nullable = false)
    private int productId;

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
    @Column(name = "pay_price")
    private int payPrice;

    @Getter
    @Setter
    @Column(name = "total_price")
    private int totalPrice;

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


}
