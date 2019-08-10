package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.Product;
import java.io.Serializable;

public class ProductForm implements Serializable {

    private String productName;
    private String productSize;
    private Integer productPrice;
    private Integer thumbnailId;
    private Integer categoryId;
    private Integer remain;
    private Integer shippingFee;
    private Integer isSoldOut;
    private Integer isDelete;
    private Product product;


    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {

        this.isDelete = isDelete;

    }

    public void setDelete(Product product) {
        product.setIsDelete(this.isDelete);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {


        if(productName != null){
            product.setProductName(this.productName);
        }
        if(productSize != null){
            product.setProductSize(this.productSize);
        }
        if(thumbnailId != null){
            product.setThumbnailId(this.thumbnailId);
        }
        if(categoryId != null){
            product.setCategoryId(this.categoryId);
        }
        if(remain!= null){
            product.setRemain(this.remain);
        }
        if(shippingFee != null){
            product.setShippingFee(this.shippingFee);
        }
        if(isSoldOut != null){
            product.setIsSoldOut(this.isSoldOut);
        }
        if(productPrice != null){
            product.setProductPrice(this.productPrice);
        }


        this.product = product;

    }

    public Integer getIsSoldOut() {
        return isSoldOut;
    }

    public void setIsSoldOut(Integer isSoldOut) {
        this.isSoldOut = isSoldOut;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }

    public Integer getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Integer shippingFee) {
        this.shippingFee = shippingFee;
    }


}