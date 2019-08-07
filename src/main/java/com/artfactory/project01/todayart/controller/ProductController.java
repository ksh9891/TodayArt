package com.artfactory.project01.todayart.controller;


import com.artfactory.project01.todayart.entity.Product;
import com.artfactory.project01.todayart.model.ResultItems;
import com.artfactory.project01.todayart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductService productService;


    @RequestMapping(
            path = "/regist",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Product create(@RequestBody Product product) {

        return productService.createProduct(product);
    }


    @RequestMapping(
            path = "/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<Product> productList() {

        return productService.listProduct();
    }



    @RequestMapping(
            path = "/{product_id}",
            method = RequestMethod.PATCH,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Product update(@PathVariable("product_id") Integer product_id, @RequestBody Product product) {
//        Optional<Product> product1 = productService.findProduct(product_id);
//        product = product1.get();
        product.setProduct_id(product_id);

        return productService.updateProduct(product_id, product);
    }

    @RequestMapping(
            path = "/{product_id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Product delete(@PathVariable("product_id") Integer product_id) {
        productService.deleteProduct(product_id);

        Product product = new Product();
        product.setProduct_id(product_id);
        return product;
    }





}
