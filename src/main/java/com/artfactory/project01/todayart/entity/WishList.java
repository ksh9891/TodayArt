package com.artfactory.project01.todayart.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="wish")
@DynamicUpdate
@DynamicInsert
public class WishList {

  @Id
  @Getter
  @Setter
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "wishlist_id", nullable = false, updatable = false)
  private Integer wishlistId;

  @Getter
  @Setter
  @Column(name = "member_id", nullable = false, updatable = false)
  private Integer memberId;

//  @Getter
//  @Setter
//  @Column(name ="product_id", nullable = false, updatable = false)
//  private Integer productId;

  @Getter
  @Setter
  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Product.class)
  @JoinColumn(name = "product_id", nullable = false, updatable = false)
  private Product product;

  @Getter
  @Setter
  @Column(name = "artist_name", nullable = false, updatable = false)
  private String artistName;

  @Getter
  @Setter
  @Column(name = "thumbnail_id", nullable = false, updatable = false)
  private Integer thumbnailId;

  @Getter
  @Setter
  @Column(name = "product_price", nullable = false, updatable = false)
  private Integer productPrice;

  @Getter
  @Setter
  @Column(name = "product_size", nullable = false, updatable = false)
  private String productSize;

  @Getter
  @Setter
  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Column(name = "enroll_dated")
  private Date enrollDated;

  @Getter
  @Setter
  @Column(name = "is_deleted", nullable = false, updatable = false)
  private Integer isDeleted;

  @Getter
  @Setter
  @Column(name = "is_stock", nullable = false, updatable = false)
  private Integer isStock;


}