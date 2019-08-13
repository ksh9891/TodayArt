package com.artfactory.project01.todayart.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
<<<<<<< HEAD
import java.io.Serializable;
import java.util.ArrayList;
=======

>>>>>>> d82492ec1ba7403c834520c8a55b0a15f7967afc
import java.util.List;

@Entity
@Table(name = "category")
@DynamicInsert
@DynamicUpdate
<<<<<<< HEAD
public class ProductCategory implements Serializable {


   /* @Id
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private List<Product> productList;*/
   @Id
   @Column(name="category_id", updatable = false, nullable = false)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer categoryId;
=======
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

>>>>>>> d82492ec1ba7403c834520c8a55b0a15f7967afc

    @Column(name = "category_name")
    private String categoryName;

<<<<<<< HEAD
//    @OneToMany(mappedBy = "category")
//    private List<Product> products = new ArrayList<>();


   /* public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }*/

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
=======








>>>>>>> d82492ec1ba7403c834520c8a55b0a15f7967afc
}
