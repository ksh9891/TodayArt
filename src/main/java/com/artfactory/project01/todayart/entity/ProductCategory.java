package com.artfactory.project01.todayart.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
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


    @Column(name = "category_name")
    private String category_name;


//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//    @JoinColumn(name = "category_id")
//    private Collection<Product> productList = new ArrayList<>();


}
