package com.artfactory.project01.todayart.entity;


<<<<<<< HEAD
import com.artfactory.project01.todayart.model.CategoryForm;
=======


import lombok.Getter;
import lombok.Setter;
>>>>>>> d82492ec1ba7403c834520c8a55b0a15f7967afc
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;

import java.util.Date;
import java.util.List;

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

<<<<<<< HEAD
//    @Column(name = "artist_id", updatable = false)
//    private Integer artistId;
=======



    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;
>>>>>>> d82492ec1ba7403c834520c8a55b0a15f7967afc


    @Column(name = "product_size")
    private String productSize;

    @Column(name = "product_price")
    private Integer productPrice;


    @Column(name = "thumbnail_id")
    private Integer thumbnailId;

    @Column(name = "is_delete")
    private Integer isDelete;



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

<<<<<<< HEAD
=======

>>>>>>> d82492ec1ba7403c834520c8a55b0a15f7967afc
    @ManyToOne
    @JoinColumn(name = "category_id")
//    @Column(name = "category_id")
    private ProductCategory productCategory;

//    @ManyToOne(targetEntity = ProductCategory.class, fetch = FetchType.EAGER)
//    @JoinColumn(name = "category_id")
//    private ProductCategory productCategory;

    @Column(name = "is_sold_out")
    private Integer isSoldOut;

    @Column(name = "remain")
    private Integer remain;

    @Column(name = "count_cart", nullable = true)
    private Integer countCart;

    @Column(name = "count_wishlist", nullable = true)
    private Integer countWishlist;

    @Column(name = "shipping_fee")
    private Integer shippingFee;

    @Column(name = "product_content")
    private String productContent;


<<<<<<< HEAD

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;



//    @ManyToOne
//    @JoinColumn(name = "artist_name")
//    private Artist artist;


    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

//    public Integer getArtistId() {
//        return artistId;
//    }
//
//    public void setArtistId(Integer artistId) {
//        this.artistId = artistId;
//    }


    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getThumbnailId() {
        return thumbnailId;
    }

    public void setThumbnailId(Integer thumbnailId) {
        this.thumbnailId = thumbnailId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Integer getIsSoldOut() {
        return isSoldOut;
    }

    public void setIsSoldOut(Integer isSoldOut) {
        this.isSoldOut = isSoldOut;
    }

    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }

    public Integer getCountCart() {
        return countCart;
    }

    public void setCountCart(Integer countCart) {
        this.countCart = countCart;
    }

    public Integer getCountWishlist() {
        return countWishlist;
    }

    public void setCountWishlist(Integer countWishlist) {
        this.countWishlist = countWishlist;
    }

    public Integer getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Integer shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Date getDeleteDated() {
        return deleteDated;
    }

    public void setDeleteDated(Date deleteDated) {
        this.deleteDated = deleteDated;
    }

    public Date getEnrollDated() {
        return enrollDated;
    }

    public void setEnrollDated(Date enrollDated) {
        this.enrollDated = enrollDated;
    }

    public Date getUpdateDated() {
        return updateDated;
    }

    public void setUpdateDated(Date updateDated) {
        this.updateDated = updateDated;
    }

=======

>>>>>>> d82492ec1ba7403c834520c8a55b0a15f7967afc
}

