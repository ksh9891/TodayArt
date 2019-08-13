package com.artfactory.project01.todayart.controller;

<<<<<<< HEAD
import com.artfactory.project01.todayart.entity.Artist;
=======
>>>>>>> d82492ec1ba7403c834520c8a55b0a15f7967afc
import com.artfactory.project01.todayart.entity.Product;
import com.artfactory.project01.todayart.model.ProductForm;
import com.artfactory.project01.todayart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductService productService;


    /*
    작성자: 채경
    기능 설명 : 새로운 상품 등록(판매자)
    @param Product Entity
    @return save된 Product 객체
    */
    @RequestMapping(
            path = "/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }


<<<<<<< HEAD
    // 상품 전체 리스트 출력 (read/get)
=======
    /*
   작성자: 채경
   기능 설명 : isDelete=0(미삭제)인 상품의 전체리스트 출력(최신 등록순으로 출력)
              (상품 전체 리스트 : 구매 고객)
   @return 전체 List<Product>객체
   */
>>>>>>> d82492ec1ba7403c834520c8a55b0a15f7967afc
    @RequestMapping(
            path = "/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
<<<<<<< HEAD
    public List<Product> retreiveProduct() {
        return productService.listProduct();
=======
    public List<Product> retrieveProduct() {
        return productService.retrieveProduct();
>>>>>>> d82492ec1ba7403c834520c8a55b0a15f7967afc
    }


    /*
   작성자: 채경
   기능 설명 : 상품 리스트 가격 오름차순 정렬 후 출력
   @return 오름차순 정렬된 List<Product> 객체
   */
    @RequestMapping(
            path = "/list/asc",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<Product> retrieveProductPriceAsc() {
        return productService.retrieveProductPriceAsc();
    }


<<<<<<< HEAD
    // 상품 가격 내림차순 출력 (read/get)
=======
    /*
   작성자: 채경
   기능 설명 : 상품 리스트 가격 내림차순 정렬 후 출력
   @return 내림차순 정렬된 List<Product> 객체
   */
>>>>>>> d82492ec1ba7403c834520c8a55b0a15f7967afc
    @RequestMapping(
            path = "/list/desc",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<Product> retrieveProductPriceDesc() {
        return productService.retrieveProductPriceDesc();
    }


    /*
    작성자: 채경
    기능 설명 : 상품 ID로 상품 상세페이지 출력
    @param Integer productId
    @return 오름차순 정렬된 List<Product> 객체
    */
    @RequestMapping(
            path = "/list/{product_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Product retrieveProductByProductId(@PathVariable("product_id") Integer productId) {
        return productService.retrieveProductDetail(productId);
    }


<<<<<<< HEAD
    // 상품 이름으로 검색(read/get)
=======
    /*
    작성자: 채경
    기능 설명 : 상품 이름(부분 이름)으로 검색 후 결과 출력
    @param String productName
    @return 상품 이름으로 검색된 List<Product> 객체
    path /productlist?name="상품명"
    */
    @RequestMapping(
            path = "/list/product",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE

    )
    public List<Product> retrieveProductByName(@RequestParam(value = "name", required = false) String productName) {

        return productService.retrieveByProductName(productName);
    }


    /*
    작성자: 채경
    기능 설명 : 카테고리를 선택하면 선택한 카테고리 상품만 출력
    @param Integer categoryId
    @return 각 카테고리별 List<Product> 객체
    */
>>>>>>> d82492ec1ba7403c834520c8a55b0a15f7967afc
    @RequestMapping(
            path = "/list/category={categoryId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<Product> retrieveProductByCategory(@PathVariable("categoryId") Integer categoryId) {

        return productService.retrieveByCategory(categoryId);
    }



    /*
    작성자: 채경
    기능 설명 : 판매자시점에서 자기가 올린 상품만 조회
    @param Integer artistId
    @return 판매자별 List<Product> 객체
    */
    @RequestMapping(
            path = "/list/artist={artistId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<Product> retrieveProductByArtistName(@PathVariable("artistId") Integer artistId) {

        return productService.retrieveByArtistId(artistId);
    }


    /*
    작성자: 채경
    기능 설명 : 작가이름으로 상품 조회/출력
    @param String artistName
    @return 각 판매자별 List<Product> 객체
    /artistlist?name=작가명
    */
    @RequestMapping(
            path = "/list/artist",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE

    )
    public List<Product> retrieveProductByArtistName(@RequestParam(value = "name", required = false) String artistName) {

        return productService.retrieveByArtistName(artistName);
    }





<<<<<<< HEAD

// 아티스트id별 상품 검색
    @RequestMapping(
            path = "/artistId={artistId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<Product> productListByArtistId(@PathVariable("artistId") Integer artistId) {

        return productService.SearchByArtist(artistId);
    }


    // 아티스트이름 검색

    @RequestMapping(
//            path = "/artist=artistName",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<Product> productListByArtistName(@RequestParam(value = "artistName", required = false) String artistName) {

        return productService.SearchByArtistName(artistName);
    }






    // 상품 정보 수정(update/patch)
=======
    /*
    작성자: 채경
    기능 설명 : 상품 개별 컬럼 업데이트
    @param Integer productId, ProductForm Model
    @return save된 Product 객체
    */
>>>>>>> d82492ec1ba7403c834520c8a55b0a15f7967afc
    @RequestMapping(
            path = "/update/{product_id}",
            method = RequestMethod.PATCH,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
<<<<<<< HEAD
    public Product updateProduct(@PathVariable("product_id") Integer product_id, @RequestBody ProductForm productForm) {
        return productService.updateProduct(product_id, productForm);
    }


    // 상품 삭제 : is_delete만 1로 변경(update/patch)
=======
    public Product updateProduct(@PathVariable("product_id") Integer productId, @RequestBody ProductForm productForm) {
        return productService.updateProduct(productId, productForm);
    }


    /*
    작성자: 채경
    기능 설명 : 판매자가 상품삭제를 누르면 is_delete 상태가 1로 바뀌며 삭제
    @param Integer productId, ProductForm Model
    @return is_delete 1로 save된 Product 객체
    */
>>>>>>> d82492ec1ba7403c834520c8a55b0a15f7967afc
    @RequestMapping(
            path = "/delete/{product_id}",
            method = RequestMethod.PATCH,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Product deleteProduct(@PathVariable("product_id") Integer product_id, @RequestBody ProductForm productForm) {
        return productService.deleteProduct(product_id, productForm);
    }


<<<<<<< HEAD
    // 상품 삭제 : 실제 DB에서 삭제됨(delete/delete)
=======
    /*
    작성자: 채경
    기능 설명 : 상품이 DB에서 삭제(관리자)
    @param Integer productId
    @return Product 객체
    */
>>>>>>> d82492ec1ba7403c834520c8a55b0a15f7967afc
    @RequestMapping(
            path = "/remove/{product_id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Product realDeleteProduct(@PathVariable("product_id") Integer product_id) {
        productService.deleteProductReal(product_id);
        Product product = new Product();
        product.setProductId(product_id);
        return product;
    }
}