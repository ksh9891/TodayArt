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
@DynamicUpdate
@DynamicInsert
@Table(name = "mileage_invoice")
public class MileageInvoice {
    @Id
    @Column(name = "mileage_id")
    @Getter
    @Setter
    private Integer mileageId;

    @Column(name = "artist_id")
    @Getter
    @Setter
    private Integer artistId;

    @Column(name = "exchange_dated")
    @Getter
    @Setter
    private Date exchangeDated;

    @Column(name = "exchange_mileage")
    @Getter
    @Setter
    private Integer exchangeMileage;

    @Column(name = "exchange_cash")
    @Getter
    @Setter
    private Integer exchangeCash;

    @Column(name = "bank_name")
    @Getter
    @Setter
    private String bankName;

    @Column(name = "deposit_account")
    @Getter
    @Setter
    private String depositAccount;

    @Column(name = "balance")
    @Getter
    @Setter
    private Integer balance;

    @Column(name = "is_delete")
    @Getter
    @Setter
    private Integer isDelete;
}
