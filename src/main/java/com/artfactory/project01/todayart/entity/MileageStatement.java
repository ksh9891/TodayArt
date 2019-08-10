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
@DynamicInsert
@DynamicUpdate
@Table(name = "mileage_statement")
public class MileageStatement {
    @Id
    @Column(name = "trade_id")
    @Getter
    @Setter
    private int tradeId;

    @Column(name = "artist_id")
    @Getter
    @Setter
    private int artistId;

    @Column(name = "order_id")
    @Getter
    @Setter
    private int orderId;

    @Column(name = "balance")
    @Nullable
    @Getter
    @Setter
    private int balance;

    @Column(name = "income")
    @Nullable
    @Getter
    @Setter
    private int income;

    @Column(name = "commission_rate")
    @Nullable
    @Getter
    @Setter
    private int commissionRate;

    @Column(name = "commission")
    @Nullable
    @Getter
    @Setter
    private int commission;

    @Column(name = "deposit_mileage")
    @Nullable
    @Getter
    @Setter
    private int depositMileage;

    @Column(name = "invoice_id")
    @Nullable
    @Getter
    @Setter
    private int invoiceId;

    @Column(name = "withdraw_mileage")
    @Nullable
    @Getter
    @Setter
    private int withdrawMileage;

    @Column(name = "create_dated")
    @Getter
    private Date createDated;

    @Column(name = "is_delete")
    @Getter
    @Setter
    private int isDelete;
}
