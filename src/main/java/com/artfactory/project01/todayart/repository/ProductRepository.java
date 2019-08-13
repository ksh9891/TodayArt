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


<<<<<<< HEAD
//    List<Product> findByProductCategory_CategoryId(Integer categoryId);
=======
>>>>>>> d82492ec1ba7403c834520c8a55b0a15f7967afc


    /*
    작성자: 채경
    기능 설명 : ProductService의 listProduct에서 사용/
               is_delete=0인 상품만 DB에서 SELECT (기본으로 최신순 정렬됨)
    */
    List<Product> findByIsDeleteOrderByEnrollDatedDesc(Integer isDelete);



    @Query(value = "SELECT p.* FROM product p, artist a\n" +
            "WHERE p.artist_id = a.artist_id\n" +
            "AND p.is_delete = 0\n " +
            "AND a.artist_name LIKE ? ", nativeQuery = true)
    List<Product> findByArtistNameAndIsDelete(String artistName);








    /*
    작성자: 채경
    기능 설명 : ProductService의 searchByProductName에서 사용/
               is_delete=0인 상품을 상품명으로 DB에서 LIKE SELECT
    */
    List<Product> findByProductNameContainingAndIsDelete(String productName, Integer IsDelete);


<<<<<<< HEAD
    // 카테고리별 검색
    List<Product> findByProductCategory_CategoryId(Integer category_id);
=======

    /*
    작성자: 채경
    기능 설명 : ProductService의 searchByCategory에서 사용/
               사용자가 선택한 카테고리별로 검색
    */
    List<Product> findByProductCategory_CategoryIdAndIsDelete(Integer categoryId, Integer isDelete);



    /*
    작성자: 채경
    기능 설명 : ProductService의 searchByArtistId에서 사용/
               판매자별 상품 검색
    */
    List<Product> findByArtist_ArtistIdAndIsDelete(Integer artistId, Integer isDelete);
>>>>>>> d82492ec1ba7403c834520c8a55b0a15f7967afc


    // 상품 가격별 오름차순 검색
    /*
    작성자: 채경
    기능 설명 : ProductService의 listProductPriceAsc에서 사용/
               productprice 오름차순으로 SELECT
    */
    List<Product> findAllByIsDeleteOrderByProductPriceAsc(Integer isDelete);


    // 상품 가격별 내림차순 검색
    /*
    작성자: 채경
    기능 설명 : ProductService의 listProductPriceDesc에서 사용/
               productprice 내림차순으로 SELECT
    */
    List<Product> findAllByIsDeleteOrderByProductPriceDesc(Integer isDelete);





    // 상품 최신등록순으로 정렬
    List<Product> findAllByIsDeleteOrderByEnrollDatedDesc(Integer isDelete);


    // 아티스트id로 검색
    List<Product> findByArtist_ArtistId(Integer artist_id);


    // 작가명으로 검색
//    List<Product> findByArtistNameContaining(String artist_name);
    List<Product> findByArtist_ArtistName(String artist_name);


}