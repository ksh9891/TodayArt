package com.artfactory.project01.todayart.entity;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@DynamicInsert
@DynamicUpdate
public class ProductCategory {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition = "int", name = "category_id", updatable = false, nullable = false)
    private Integer category_id;

    @Column(name = "category_name")
    private String category_name;


//    @OneToMany(mappedBy="category")
//    private List<Product> products = new ArrayList<Product>();

}
