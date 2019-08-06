package com.artfactory.project01.todayart.entity;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class ProductCategory {


    @Id
    @Column(columnDefinition = "int", name = "category_id", updatable = false, nullable = false)
    private Integer category_id;

    @Column(name = "category_name")
    private String category_name;

}
