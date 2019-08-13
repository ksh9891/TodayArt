package com.artfactory.project01.todayart.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "product")
@DynamicInsert
@DynamicUpdate
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition = "int", name = "product_id", nullable = false, updatable = false)
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "artist_id", updatable = false)
    private Integer artistId;


    @Column(name = "product_size")
    private String productSize;

    @Column(name = "product_price")
    private Integer productPrice;


    @Column(name = "thumbnail_id")
    private Integer thumbnailId;

    @Column(name = "is_delete")
    private Integer isDelete;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "delete_dated", nullable = true)
    private Date deleteDated;

    @Column(name = "enroll_dated", nullable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date enrollDated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_dated", nullable = true)
    private Date updateDated;

    @Column(name = "category_id")
    private Integer categoryId;

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

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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
}

