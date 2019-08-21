package com.artfactory.project01.todayart.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "artist")
@Getter
@Setter
@DynamicInsert
public class Artist implements Serializable {

    @Id
    @Column(name = "artist_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer artistId;

    @Column(name = "member_id")
    private Integer memberId;

    @JoinColumn(table = "member", name = "realname")
    @Column(name = "artist_name", updatable = false, nullable = false)
    private String artistName;

    @Column(name = "artist_desc")
    private String artistDesc;

    @Column(name = "profile_id")
    private Integer profileId;

    @Column(name = "adm_product_image")
    private Integer admProductImage;

    @Column(name = "adm_product_desc")
    private String admProductDesc;

    @Column(name = "adm_check")
    private Integer admCheck;

    @JoinColumn(table = "artist_level", name = "artist_level_id")
    @Column(name = "artist_level_id")
    private Integer artistLevelId;

    @Column(name = "adm_apply_dated")
    private Date admApplyDated;

    @Column(name = "adm_check_dated")
    private Date admCheckDated;
}

