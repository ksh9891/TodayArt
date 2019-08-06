package com.artfactory.project01.todayart.entity;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
@DynamicUpdate
@DynamicInsert
public class Product {

    @Id
    @Column(columnDefinition = "int", name = "product_id", nullable = false, updatable = false)
    private Integer product_id;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "artist_id")
    private Integer artist_id;


    @Column(name = "product_size")
    private String product_size;

    @Column(name = "product_price")
    private Integer product_price;


    @Column(name = "thumbnail_id")
    private Integer thumbnail_id;

    @Column(name = "is_delete")
    private Integer is_delete;

    @Column(name = "delete_dated", nullable = true)
    private Date delete_dated;

    @Column(name = "enroll_dated", nullable = true)
    private Date enroll_dated;

    @Column(name = "update_dated", nullable = true)
    private Date update_dated;

    @Column(name = "category")
    private Integer category;

    @Column(name = "is_sold_out")
    private Integer is_sold_out;

    @Column(name = "remain")
    private Integer remain;

    @Column(name = "count_cart")
    private Integer count_cart;

    @Column(name = "count_wishlist")
    private Integer count_wishlist;

    @Column(name = "shipping_fee")
    private Integer shipping_fee;


    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(Integer artist_id) {
        this.artist_id = artist_id;
    }

    public String getProduct_size() {
        return product_size;
    }

    public void setProduct_size(String product_size) {
        this.product_size = product_size;
    }

    public Integer getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Integer product_price) {
        this.product_price = product_price;
    }

    public Integer getThumbnail_id() {
        return thumbnail_id;
    }

    public void setThumbnail_id(Integer thumbnail_id) {
        this.thumbnail_id = thumbnail_id;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Date getDelete_dated() {
        return delete_dated;
    }

    public void setDelete_dated(Date delete_dated) {
        this.delete_dated = delete_dated;
    }

    public Date getEnroll_dated() {
        return enroll_dated;
    }

    public void setEnroll_dated(Date enroll_dated) {
        this.enroll_dated = enroll_dated;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getIs_sold_out() {
        return is_sold_out;
    }

    public void setIs_sold_out(Integer is_sold_out) {
        this.is_sold_out = is_sold_out;
    }

    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }

    public Integer getCount_cart() {
        return count_cart;
    }

    public void setCount_cart(Integer count_cart) {
        this.count_cart = count_cart;
    }

    public Integer getCount_wishlist() {
        return count_wishlist;
    }

    public void setCount_wishlist(Integer count_wishlist) {
        this.count_wishlist = count_wishlist;
    }

    public Integer getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(Integer shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public Date getUpdate_dated() {
        return update_dated;
    }

    public void setUpdate_dated(Date update_dated) {
        this.update_dated = update_dated;
    }
}


