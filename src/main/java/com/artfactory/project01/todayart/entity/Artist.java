package com.artfactory.project01.todayart.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "artist")
@Data
public class Artist implements Serializable {

    @Id
    @Column(name = "artist_id", updatable = false, nullable = false)
    private Integer artistId;

    @JoinColumn(table = "member",name = "member_id")
    @Column(name = "member_id", updatable = false, nullable = false)
    private Integer memberId;

    @Column(name = "artist_desc")
    private String artistDesc;

    @Column(name = "profile_id")
    private Integer profileId;

    @Column(name = "adm_product_desc")
    private String admProductDesc;

    @Column(name = "adm_check")
    private Integer admCheck;

    @Column(name = "artist_level_id")
    private Integer artistLevelId;
}
