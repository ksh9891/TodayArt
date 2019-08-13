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
    기능 설명 : 등록된 상품중 삭제되지 않은 모든 상품 리스트 조회
    <GET>
    @param Integer isDelete
    @return 각 카테고리별 Product<List> 객체
    */
    @Transactional(readOnly = true)
    public List<Product> listProduct() {
        Integer isDelete = 0;
        return productRepository.findByIsDeleteOrderByEnrollDated(isDelete);
    }


     /*
    작성자: 수지
    기능 설명 : 등록된 상품 가격 오름차순으로 조회(저렴한 가격-> 비싼가격 순으로 조회)
    <GET>
    @param Integer categoryId
    @return 각 카테고리별 Product<List> 객체
    */
    @Transactional(readOnly = true)
    public List<Product> listProductPriceAsc() {
        return productRepository.findAllByOrderByProductPriceAsc();
    }


    /*
    작성자: 수지
    기능 설명 : 등록된 상품 가격 내림차순으로 조회(비싼 가격-> 저렴한 순으로 조회)
    <GET>
    @param Integer categoryId
    @return 각 카테고리별 Product<List> 객체
    */
    @Transactional(readOnly = true)
    public List<Product> listProductPriceDesc() {
        return productRepository.findAllByOrderByProductPriceDesc();
    }



    /*
    작성자: 수지
    기능 설명 : 상품 상세 페이지
    <GET>
    @param Integer product_id
    @return 각 카테고리별 Product<List> 객체
    */
    @Transactional(readOnly = true)
    public Product listProductdetail(Integer product_id) {
        return productRepository.findById(product_id).get();
    }


    /*
    작성자: 수지
    기능 설명 : 상품 이름(productName)으로 검색
    <GET>
    @param String productName
    @return 각 카테고리별 Product<List> 객체
    */
    @Transactional(readOnly = true)
    public List<Product> searchByProductName(String productName) {

        Integer categoryId = 0;
        return productRepository.findByProductNameContainingAndIsDelete(productName, categoryId);
    }


    /*
    작성자: 수지
    기능 설명 : 상품 카테고리별 검색
    <GET>
    @param Integer categoryId
    @return 각 카테고리별 Product<List> 객체
    */
    @Transactional(readOnly = true)
    public List<Product> searchByCategory(Integer category_id) {

        return productRepository.findByProductCategory_CategoryId(category_id);
    }


    /*
    작성자: 수지
    기능 설명 : 상품 정보 수정(상품별 컬럼 개별업데이트)
    <PATCH>
    @param Integer id, ProductForm productForm
    @return Product 객체
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
    @param Integer categoryId
    */
    @Transactional
    public void deleteProductReal(Integer id) {
        productRepository.deleteById(id);
    }
    }