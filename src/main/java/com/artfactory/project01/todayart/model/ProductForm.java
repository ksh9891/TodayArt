package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.Product;
import com.artfactory.project01.todayart.entity.ProductCategory;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
public class ProductForm implements Serializable {

    private String productName;
    private String productSize;
    private Integer productPrice;
    private Integer thumbnailId;
    private Integer remain;
    private Integer shippingFee;
    private Integer isSoldOut;
    private Integer isDelete;
    private String productContent;
    private Product product;
    private Date deleteDated;
    private Integer categoryId;
    private ProductCategory productCategory;





    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {

        this.isDelete = isDelete;

    }

    public void setDelete(Product product) {
        product.setIsDelete(this.isDelete);
        product.setDeleteDated(new Date());
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
        if(productCategory != null){
            product.setProductCategory(productCategory);
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
        if(productContent != null){
            product.setProductContent(this.productContent);
        }



        this.product = product;

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

    public Integer getIsSoldOut() {
        return isSoldOut;
    }

    public void setIsSoldOut(Integer isSoldOut) {
        this.isSoldOut = isSoldOut;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public Date getDeleteDated() {
        return deleteDated;
    }

    public void setDeleteDated(Date deleteDated) {
        this.deleteDated = deleteDated;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}