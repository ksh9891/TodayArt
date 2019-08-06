package com.artfactory.project01.todayart.service;


import com.artfactory.project01.todayart.entity.Product;
import com.artfactory.project01.todayart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> listProduct() {
        return productRepository.findAll();
    }



    @Transactional
    public Product updateProduct(Integer id, Product product) {
//        productRepository.findById(product_id);

        return productRepository.save(product);
    }

}
