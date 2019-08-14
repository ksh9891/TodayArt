package com.artfactory.project01.todayart.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/*
 * 작성자 : 상현
 * 기능 : Shipping_items 엔티티
 */

@Entity
@Table(name = "shipping_items")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class ShippingItems {
    @Id
    @Column(name = "item_id")
    private Integer itemId; // Shipping Items 아이디

    @Column(name = "shipping_id")
    private Integer shippingId; // 배송 번호

    @Column(name = "ordered_id")
    private Integer orderedId; // 주문 번호

    @Column(name = "product_id")
    private Integer productId; // 상품 번호

    @Column(name = "last_updated")
    private Date lastUpdated; // status 최종 수정 날짜

    @Column(name = "status")
    private String status; // 배송 상태
}
