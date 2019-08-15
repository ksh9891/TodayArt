package com.artfactory.project01.todayart.repository;

import com.artfactory.project01.todayart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    ArrayList<Cart> findAllByMemberIdAndIsDeleted(int memberId, int isDeleted);

    @Query(value = "SELECT * FROM cart WHERE member_id = ?1 AND product_id = ?2 And is_deleted = 0", nativeQuery = true)
    Cart findByMemberIdAndProducIdtAndIsDelete(Integer memberId, Integer productId);
}
