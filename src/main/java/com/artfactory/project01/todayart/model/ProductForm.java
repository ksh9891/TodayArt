package com.artfactory.project01.todayart.model;



import com.artfactory.project01.todayart.entity.Product;

import java.util.Date;


public class ProductForm {

    private String product_name;
    private String product_size;
    private Integer product_price;
    private Integer thumbnail_id;
    private Date update_dated;
    private Integer category;
    private Integer remain;
    private Integer shipping_fee;


    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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

    public Date getUpdate_dated() {
        return update_dated;
    }

    public void setUpdate_dated(Date update_dated) {
        this.update_dated = update_dated;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }

    public Integer getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(Integer shipping_fee) {
        this.shipping_fee = shipping_fee;
    }
    public Product setProduct(){
        Product product = new Product();
        product.setProduct_name(this.product_name);
        product.setProduct_price(this.product_price);
        product.setProduct_size(this.product_size);
        product.setThumbnail_id(this.thumbnail_id);
        product.setUpdate_dated(this.update_dated);
        product.setCategory(this.category);
        product.setRemain(this.remain);
        product.setShipping_fee(this.shipping_fee);

        return product;

    }


}
