package com.artfactory.project01.todayart.service;


import com.artfactory.project01.todayart.entity.Product;
import com.artfactory.project01.todayart.model.ProductForm;
import com.artfactory.project01.todayart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public Product updateProduct(Integer id, ProductForm productForm) {
        Product product = productRepository.findById(id).get();

        productForm.setProduct(product);


        return productRepository.save(product);
    }


    @Transactional
    public Product deleteProduct(Integer id, ProductForm productForm){
        Product product = productRepository.findById(id).get();
        productForm.setDelete(product);


        return productRepository.save(product);

    }


    @Transactional
    public void deleteProductReal(Integer id) {
        productRepository.deleteById(id);
    }




}
