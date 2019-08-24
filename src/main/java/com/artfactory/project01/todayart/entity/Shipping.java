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

/*
 * 작성자 : 상현
 * 설명 : Shipping 엔티티
 */
@Entity
@Table(name = "shipping")
@DynamicUpdate
@DynamicInsert
public class Shipping {
    @Id
    @Column(name = "shipping_id", insertable = false, nullable = false, updatable = false)
    @Getter
    @Setter
    private Integer shippingId; // 배송번호

    @Column(name = "ordered_id")
    @Getter
    @Setter
    private Integer orderedId; // 주문번호

    @Column(name = "shipping_dated", insertable = false)
    @Getter
    @Setter
    @Nullable
    private Date shippingDated; // 배송 시작일

    @Column(name = "shipping_fee")
    @Getter
    @Setter
    private Integer shippingFee; // 배송료

    @Column(name = "consignee")
    @Getter
    @Setter
    private String consignee; // 수취인

    @Column(name = "postal_code")
    @Getter
    @Setter
    private Integer postalCode; // 우편번호

    @Column(name = "receive_addr")
    @Getter
    @Setter
    private String receiveAddr; // 수취인 주소

    @Column(name = "consignee_phone")
    @Getter
    @Setter
    private String consigneePhone; // 수취인 번호

    @Column(name = "artist_id")
    @Getter
    @Setter
    private Integer artistId; // 판매자 PK (아티스트)

    @Column(name = "member_id")
    @Getter
    @Setter
    private Integer memberId; // 구매자 PK (일반)

    @Column(name = "courier")
    @Getter
    @Setter
    private String courier; // 택배사

    @Column(name = "tracking_number", insertable = false)
    @Getter
    @Setter
    @Nullable
    private String trackingNumber; // 송장번호

    @Column(name = "is_delete")
    @Getter
    @Setter
    private String isDelete; // 삭제여부
}
