package com.artfactory.project01.todayart.repository;

import com.artfactory.project01.todayart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    ArrayList<Cart> findAllByMemberIdAndIsDeleted(int memberId, String isDeleted);

    @Query(value = "SELECT * FROM cart WHERE member_id = ?1 AND product_id = ?2 And is_deleted = 'n'", nativeQuery = true)
    Cart findByMemberIdAndProductIdtAndIsDelete(Integer memberId, Integer productId);
}
