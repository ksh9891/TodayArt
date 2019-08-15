package com.artfactory.project01.todayart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @Column(name = "artist_name")
    private String artistName;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member memberId;

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

