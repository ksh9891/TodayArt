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


    // is_delete 업데이트 시 delete_dated 현재 시간으로 업데이트
    @Modifying    // update , delete Query시 @Modifying 어노테이션을 추가
    @Query(value="UPDATE product SET delete_dated = now() WHERE product_id = ?", nativeQuery=true)
    void deleteDate(Integer product_id);


    // 상품 업데이트 시 update_dated 현재 시간으로 업데이트
    @Modifying
    @Query(value="UPDATE product SET update_dated = now() WHERE product_id = ?", nativeQuery=true)
    void updateDate(Integer product_id);


    // 상품명으로 검색
    @Query(value = "SELECT * FROM Product WHERE product_name LIKE ? ", nativeQuery = true)
    List<Product> findByProduct_nameLike(String product_name);


    // 카테고리별 검색
    @Query(value = "SELECT * FROM Product WHERE category_id = ? ", nativeQuery = true)
    List<Product> findByCategory_idLike(Integer category_id);


    // 삭제되지 않은 상품만 검색
    @Query(value = "SELECT * FROM Product WHERE is_delete = 0 ", nativeQuery = true)
    List<Product> findNotDelete();


    // 상품 가격별 오름차순 검색
    @Query(value = "SELECT * FROM Product ORDER BY product_price asc ", nativeQuery = true)
    List<Product> findProductsByPriceASC();


    // 상품 가격별 내림차순 검색
    @Query(value = "SELECT * FROM Product ORDER BY product_price desc ", nativeQuery = true)
    List<Product> findProductsByPriceDESC();





}