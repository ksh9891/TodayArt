package com.artfactory.project01.todayart.repository;
import com.artfactory.project01.todayart.entity.Product;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {




    /*
    작성자: 채경
    기능 설명 : ProductService의 listProduct에서 사용/
               is_delete=0인 상품만 DB에서 SELECT (기본으로 최신순 정렬됨)
    */
    List<Product> findByIsDeleteOrderByEnrollDated(Integer isDelete);




    /*
    작성자: 채경
    기능 설명 : ProductService의 searchByProductName에서 사용/
               is_delete=0인 상품을 상품명으로 DB에서 LIKE SELECT
    */
    List<Product> findByProductNameContainingAndIsDelete(String productName, Integer IsDelete);



    /*
    작성자: 채경
    기능 설명 : ProductService의 searchByCategory에서 사용/
               사용자가 선택한 카테고리별로 검색
    */
    List<Product> findByProductCategory_CategoryId(Integer category_id);


    // 상품 가격별 오름차순 검색
    /*
    작성자: 채경
    기능 설명 : ProductService의 listProductPriceAsc에서 사용/
               productprice 오름차순으로 SELECT
    */
    List<Product> findAllByOrderByProductPriceAsc();


    // 상품 가격별 내림차순 검색
    /*
    작성자: 채경
    기능 설명 : ProductService의 listProductPriceDesc에서 사용/
               productprice 내림차순으로 SELECT
    */
    List<Product> findAllByOrderByProductPriceDesc();


    //List<Product> findAllByProductCategory_CategoryId(Integer categoryId);



}