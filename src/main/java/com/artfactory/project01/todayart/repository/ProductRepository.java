package com.artfactory.project01.todayart.repository;
import com.artfactory.project01.todayart.entity.Artist;
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
    List<Product> findByIsDeleteOrderByEnrollDatedDesc(String isDelete);



    @Query(value = "SELECT p.* FROM product p, artist a\n" +
            "WHERE p.artist_id = a.artist_id\n" +
            "AND p.is_delete = 'n'\n " +
            "AND a.artist_name LIKE ? ", nativeQuery = true)
    List<Product> findByArtistNameAndIsDelete(String artistName);








    /*
    작성자: 채경
    기능 설명 : ProductService의 searchByProductName에서 사용/
               is_delete=0인 상품을 상품명으로 DB에서 LIKE SELECT
    */
    List<Product> findByProductNameContainingAndIsDelete(String productName, String IsDelete);






    /*
    작성자: 채경
    기능 설명 : ProductService의 searchByCategory에서 사용/
               사용자가 선택한 카테고리별로 검색
    */
    List<Product> findByProductCategory_CategoryIdAndIsDeleteOrderByEnrollDatedDesc(Integer categoryId, String isDelete);

    List<Product> findByProductCategory_CategoryIdAndIsDeleteOrderByProductPriceAsc(Integer categoryId, String isDelete);

    List<Product> findByProductCategory_CategoryIdAndIsDeleteOrderByProductPriceDesc(Integer categoryId, String isDelete);



    /*
    작성자: 채경
    기능 설명 : ProductService의 searchByArtistId에서 사용/
               판매자별 상품 검색
    */
    List<Product> findByArtist_ArtistIdAndIsDelete(Integer artistId, String isDelete);


    // 상품 가격별 오름차순 검색
    /*
    작성자: 채경
    기능 설명 : ProductService의 listProductPriceAsc에서 사용/
               productprice 오름차순으로 SELECT
    */
    List<Product> findAllByIsDeleteOrderByProductPriceAsc(String isDelete);


    // 상품 가격별 내림차순 검색
    /*
    작성자: 채경
    기능 설명 : ProductService의 listProductPriceDesc에서 사용/
               productprice 내림차순으로 SELECT
    */
    List<Product> findAllByIsDeleteOrderByProductPriceDesc(String isDelete);









}