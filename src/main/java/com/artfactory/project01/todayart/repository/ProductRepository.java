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


    List<Product> findByProductCategory_CategoryId(Integer categoryId);

    // 삭제되지 않은 상품만 검색
    List<Product> findByIsDelete(Integer isDelete);

    // 상품명으로 검색
    List<Product> findByProductNameContainingAndIsDelete(String productName, Integer IsDelete);


    // 카테고리별 검색
    List<Product> findByCategoryId(Integer category_id);


    // 상품 가격별 오름차순 검색
    List<Product> findAllByOrderByProductPriceAsc();


    // 상품 가격별 내림차순 검색
    List<Product> findAllByOrderByProductPriceDesc();





}