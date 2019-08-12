package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.Product;
import com.artfactory.project01.todayart.entity.ProductCategory;

public class CategoryForm {

    private Integer categoryId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }


    public ProductCategory setProductCategory() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategory_id(categoryId);

        return productCategory;
    }
}
