package com.artfactory.project01.todayart.model;
import com.artfactory.project01.todayart.entity.Product;
import java.io.Serializable;





public class ProductForm implements Serializable {



    private String product_name;
    private String product_size;
    private Integer product_price;
    private Integer thumbnail_id;
    private Integer category_id;
    private Integer remain;
    private Integer shipping_fee;
    private Integer is_sold_out;
    private Integer is_delete;
    private Product product;


    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {

        this.is_delete = is_delete;

    }

    public void setDelete(Product product) {
        product.setIs_delete(this.is_delete);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {


        if(product_name != null){
            product.setProduct_name(this.product_name);
        }
        if(product_size != null){
            product.setProduct_size(this.product_size);
        }
        if(thumbnail_id != null){
            product.setThumbnail_id(this.thumbnail_id);
        }
        if(category_id != null){
            product.setCategory_id(this.category_id);
        }
        if(remain!= null){
            product.setRemain(this.remain);
        }
        if(shipping_fee!= null){
            product.setShipping_fee(this.shipping_fee);
        }
        if(is_sold_out != null){
            product.setIs_sold_out(this.is_sold_out);
        }
        if(product_price != null){
            product.setProduct_price(this.product_price);
        }


        this.product = product;

    }

    public Integer getIs_sold_out() {
        return is_sold_out;
    }

    public void setIs_sold_out(Integer is_sold_out) {
        this.is_sold_out = is_sold_out;
    }

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

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
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


}
