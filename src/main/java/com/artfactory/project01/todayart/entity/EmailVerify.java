package com.artfactory.project01.todayart.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name="email_verify")
@Getter
@Setter
public class EmailVerify {
    @Id
    @Column(name = "verify_id")
    private Integer verifyId;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "type")
    private String type;

    @Column(name = "expire_dated")
    private Date expireDated;

    @Column(name="verify_url")
    private String verify_url;
}
