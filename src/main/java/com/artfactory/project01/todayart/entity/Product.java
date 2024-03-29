package com.artfactory.project01.todayart.entity;





import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;


import java.util.Date;

@Entity
@Table(name = "product")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition = "int", name = "product_id", nullable = false, updatable = false)
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column(name = "artist_name")
    private String artistName;

    @Column(name = "product_size")
    private String productSize;

    @Column(name = "product_price")
    private Integer productPrice;

    @Column(name = "thumbnail_id", insertable = false, updatable = false)
    private Integer thumbnailId;

    @Column(name = "is_delete")
    private String isDelete;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "delete_dated", nullable = true)
    private Date deleteDated;

    @CreationTimestamp
    @Column(name = "enroll_dated", nullable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date enrollDated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_dated", nullable = true)
    private Date updateDated;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

    @Column(name = "is_sold_out")
    private String isSoldOut;

    @Column(name = "remain")
    private Integer remain;

    @Column(name = "count_cart")
    private Integer countCart;

    @Column(name = "count_wishlist")
    private Integer countWishlist;

    @Column(name = "shipping_fee")
    private Integer shippingFee;

    @Column(name = "product_content")
    private String productContent;

    @OneToOne(targetEntity=File.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "thumbnail_id", referencedColumnName = "file_id")
    private File thumbnail;
}

