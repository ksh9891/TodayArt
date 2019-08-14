package com.artfactory.project01.todayart.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "shipping")
@DynamicUpdate
@DynamicInsert
public class Shipping {
    @Id
    @Column(name = "shipping_id", insertable = false, nullable = false, updatable = false)
    @Getter
    @Setter
    private int shippingId;

    @Column(name = "ordered_id")
    @Getter
    @Setter
    private int orderedId;

    @Column(name = "shipping_dated", insertable = false)
    @Getter
    @Setter
    @Nullable
    private Date shippingDated;

    @Column(name = "shipping_fee")
    @Getter
    @Setter
    private int shippingFee;

    @Column(name = "consignee")
    @Getter
    @Setter
    private String consignee;

    @Column(name = "postal_code")
    @Getter
    @Setter
    private int postalCode;

    @Column(name = "receive_addr")
    @Getter
    @Setter
    private String receiveAddr;

    @Column(name = "consignee_phone")
    @Getter
    @Setter
    private String consigneePhone;

    @Column(name = "artist_id")
    @Getter
    @Setter
    private int artistId;

    @Column(name = "member_id")
    @Getter
    @Setter
    private int memberId;

    @Column(name = "courier")
    @Getter
    @Setter
    private String courier;

    @Column(name = "tracking_number", insertable = false)
    @Getter
    @Setter
    @Nullable
    private String trackingNumber;

    @Column(name = "is_delete")
    @Getter
    @Setter
    private int isDelete;
}
