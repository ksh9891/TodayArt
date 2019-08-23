package com.artfactory.project01.todayart.entity;

import com.artfactory.project01.todayart.model.kakao.KakaoAmount;
import com.artfactory.project01.todayart.model.kakao.KakaoCard;
import com.artfactory.project01.todayart.util.KakaoAmountToJsonConverter;
import com.artfactory.project01.todayart.util.KakaoCardToStringConverter;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="pg_kakaopay")
public class KakaoInfoRequest implements Serializable {
    @Id
    @Column(name="aid")
    private String aid;
    @Column(name="tid")
    private String tid;
    @Column(name="cid")
    private String cid;
    @Column(name="sid")
    private String sid;
    @Column(name="partner_order_id")
    private String partner_order_id;
    @Column(name="partner_user_id")
    private String partner_user_id;
    @Transient
    private String payment_method_type;
    @Column(name="amount")
    @Convert(converter = KakaoAmountToJsonConverter.class)
    private KakaoAmount amount;
    @Column(name="card_info")
    @Convert(converter = KakaoCardToStringConverter.class)
    private KakaoCard card_info;
    @Column(name="item_name")
    private String item_name;
    @Column(name="item_code")
    private String item_code;
    @Column
    private String payload;
    @Column(name="quantity")
    private Integer quantity;
    @Transient
    private Integer tax_free_amount;
    @Transient
    private Integer vat_amount;
    @Column(name="created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
    @Column(name="approved_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approved_at;
}
