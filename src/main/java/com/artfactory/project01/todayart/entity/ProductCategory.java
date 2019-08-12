package com.artfactory.project01.todayart.entity;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@DynamicInsert
@DynamicUpdate
public class ProductCategory {


    @Id
    @Column(columnDefinition = "int", name = "category_id", updatable = false, nullable = false)
    private Integer category_id;

    @Column(name = "category_name")
    private String category_name;

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
