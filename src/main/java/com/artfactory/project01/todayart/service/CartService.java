package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class CartService implements Serializable {
    @Autowired
    CartRepository cartRepository;
}
