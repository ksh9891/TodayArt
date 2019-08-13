package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Product;
import com.artfactory.project01.todayart.model.ProductForm;
import com.artfactory.project01.todayart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;




    /*
     작성자: 수지
     기능 설명 : 상품등록
     <POST>
     @param Product product
     @return Product 객체
     */
    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }


    /*
   작성자: 수지
   기능 설명 : 등록된 상품중 삭제되지 않은 모든 상품 리스트 조회(최신등록순)
   <GET>
   @return List<Product> 객체
   */
    @Transactional(readOnly = true)
    public List<Product> retrieveProduct() {
        // is_delete를 0인 것만 검색하기 위함
        Integer isDelete = 0;
        return productRepository.findByIsDeleteOrderByEnrollDatedDesc(isDelete);
    }


    /*
   작성자: 수지
   기능 설명 : 등록된 상품 가격 오름차순으로 조회(저렴한 가격-> 비싼가격 순으로 조회)
   <GET>
   @return 오름차순 정렬된 List<Product> 객체
   */
    @Transactional(readOnly = true)
    public List<Product> retrieveProductPriceAsc() {
        // is_delete를 0인 것만 검색하기 위함
        Integer isDelete = 0;
        return productRepository.findAllByIsDeleteOrderByProductPriceAsc(isDelete);
    }


    /*
    작성자: 수지
    기능 설명 : 등록된 상품 가격 내림차순으로 조회(비싼 가격-> 저렴한 순으로 조회)
    <GET>
    @return 내림차순 정렬된 List<Product> 객체
    */
    @Transactional(readOnly = true)
    public List<Product> retrieveProductPriceDesc() {

        // is_delete를 0인 것만 검색하기 위함
        Integer isDelete = 0;
        return productRepository.findAllByIsDeleteOrderByProductPriceDesc(isDelete);
    }



    /*
    작성자: 수지
    기능 설명 : 상품 상세 페이지
    <GET>
    @param Integer product_id
    @return 각 상품별 Product 객체
    */
    @Transactional(readOnly = true)
    public Product retrieveProductDetail(Integer productId) {
        return productRepository.findById(productId).get();
    }






    /*
    작성자: 수지
    기능 설명 : 상품 이름(productName)으로 검색
    <GET>
    @param String productName
    @return 상품명으로 조회된 List<Product> 객체
    */
    @Transactional(readOnly = true)
    public List<Product> retrieveByProductName(String productName) {

        // is_delete를 0인 것만 검색하기 위함
        Integer isDelete = 0;
        return productRepository.findByProductNameContainingAndIsDelete(productName, isDelete);
    }


    /*
    작성자: 수지
    기능 설명 : 상품 카테고리별 검색
    <GET>
    @param Integer categoryId
    @return 각 카테고리별 List<Product> 객체
    */
    @Transactional(readOnly = true)
    public List<Product> retrieveByCategory(Integer category_id) {


        // is_delete를 0인 것만 검색하기 위함
        Integer isDelete = 0;

        return productRepository.findByProductCategory_CategoryIdAndIsDelete(category_id, isDelete);
    }


    /*
    작성자: 수지
    기능 설명 : 상품을 판매자 아이디로 검색
    <GET>
    @param Integer artistId
    @return 각 판매자 아이디별 List<Product> 객체
    */
    @Transactional(readOnly = true)
    public List<Product> retrieveByArtistId(Integer artistId) {

        // is_delete를 0인 것만 검색하기 위함
        Integer isDelete = 0;

        return productRepository.findByArtist_ArtistIdAndIsDelete(artistId, isDelete);
    }



    /*
    작성자: 수지
    기능 설명 : 아티스트 이름으로 검색
    <GET>
    @param String artistName
    @return 아티스트 이름으로 검색한 List<Product> 객체
    */
    @Transactional(readOnly = true)
    public List<Product> retrieveByArtistName(String artistName) {
        // Like 검색을 위해 앞뒤로 % 달아줌
        artistName = "%" + artistName +"%";

        return productRepository.findByArtistNameAndIsDelete(artistName);
    }





    /*
    작성자: 수지
    기능 설명 : 상품 정보 수정(상품별 컬럼 개별업데이트)
    <PATCH>
    @param Integer id, ProductForm productForm
    @return save된 Product 객체
    */
    @Transactional
    public Product updateProduct(Integer id, ProductForm productForm) {
        Product product = productRepository.findById(id).get();

        productForm.setProduct(product);
        return productRepository.save(product);
    }


    /*
    작성자: 수지
    기능 설명 : 상품 삭제(is_delete=1로 변경)
    setDelete 는 delete_dated update를 위한 메서드
    <PATCH>
    @param Integer id, ProductForm productForm
    @return Product 객체
    */
    @Transactional
    public Product deleteProduct(Integer id, ProductForm productForm) {
        Product product = productRepository.findById(id).get();
        productForm.setDelete(product);

        return productRepository.save(product);
    }


    /*
    작성자: 수지
    기능 설명 : 상품 삭제(DB에서 삭제됨)
    <DELETE>
    @param Integer productId
    */
    @Transactional
    public void deleteProductReal(Integer productId) {
        productRepository.deleteById(productId);
    }
}