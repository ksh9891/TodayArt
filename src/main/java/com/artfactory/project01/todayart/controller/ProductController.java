package com.artfactory.project01.todayart.controller;
import com.artfactory.project01.todayart.entity.Product;
import com.artfactory.project01.todayart.model.ProductForm;
import com.artfactory.project01.todayart.model.Search;
import com.artfactory.project01.todayart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductService productService;


    // 상품 등록(create/post)
    @RequestMapping(
            path = "/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Product create(@RequestBody Product product) {
        return productService.createProduct(product);
    }



    // 상품 전체 리스트 출력 (read/get)
    @RequestMapping(
            path = "/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<Product> productList() {
        return productService.listProduct();
    }


    // 상품 가격 오름차순 출력 (read/get)
    @RequestMapping(
            path = "/list/asc",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<Product> productListPriceAsc() {
        return productService.listProductPriceAsc();
    }



    // 상품 가격 내림차순 출력 (read/get)
    @RequestMapping(
            path = "/list/desc",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<Product> productListPriceDesc() {
        return productService.listProductPriceDesc();
    }



    // 상품 아이디별 조회(상품 상세 페이지, read/get)
    @RequestMapping(
            path = "/list/{product_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Product productList(@PathVariable("product_id") Integer product_id) {
        return productService.listProductdetail(product_id);
    }




    // 상품 이름으로 검색(read/get)
    @RequestMapping(
            path = "/lists",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE

    )
    public List<Product> productListByName(@RequestParam(value = "name", required = false) String productName) {

        return productService.SearchByProductName(productName);
    }


    // 상품 카테고리별 검색(read/get)
    @RequestMapping(
            path = "/category={categoryId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<Product> productListByCategory(@PathVariable("categoryId") Integer categoryId) {

        return productService.SearchByCategory(categoryId);
    }





    // 상품 정보 수정(update/patch)
    @RequestMapping(
            path = "/update/{product_id}",
            method = RequestMethod.PATCH,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Product update(@PathVariable("product_id") Integer product_id, @RequestBody ProductForm productForm) {
        return productService.updateProduct(product_id, productForm);
    }




    // 상품 삭제 : is_delete만 1로 변경(update/patch)
    @RequestMapping(
            path = "/delete/{product_id}",
            method = RequestMethod.PATCH,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Product delete(@PathVariable("product_id") Integer product_id, @RequestBody ProductForm productForm) {
        return productService.deleteProduct(product_id, productForm);
    }





    // 상품 삭제 : 실제 DB에서 삭제됨(delete/delete)
    @RequestMapping(
            path = "/remove/{product_id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Product deleteReal(@PathVariable("product_id") Integer product_id) {
        productService.deleteProductReal(product_id);
        Product product = new Product();
        product.setProductId(product_id);
        return product;
    }
}