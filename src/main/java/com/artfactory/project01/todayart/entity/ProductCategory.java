package com.artfactory.project01.todayart.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class ProductCategory {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;


//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "category_id")
//    private List<Product> productList;


    @Column(name = "category_name")
    private String category_name;









}
