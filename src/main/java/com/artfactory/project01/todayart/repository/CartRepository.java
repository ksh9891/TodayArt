package com.artfactory.project01.todayart.repository;

import com.artfactory.project01.todayart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    ArrayList<Cart> findAllByMemberIdAndIsDeleted(int memberId, int isDeleted);
}