package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.Product;
import com.artfactory.project01.todayart.entity.ProductCategory;

import java.io.Serializable;

public class CategoryForm implements Serializable {

    private Integer categoryId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }


    public ProductCategory setProductCategory(Integer categoryId) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(categoryId);

        return productCategory;
    }
}
