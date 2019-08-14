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
    private Integer itemId;

    @Column(name = "shipping_id")
    private Integer shippingId;

    @Column(name = "ordered_id")
    private Integer orderedId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "last_updated")
    private Date lastUpdated;

    @Column(name = "status")
    private String status;
}
