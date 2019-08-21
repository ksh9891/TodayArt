package com.artfactory.project01.todayart.repository;


import com.artfactory.project01.todayart.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {


    ArrayList<WishList> findAllByMemberIdAndIsDelete(int memberId, int isDelete);




    @Query(value = "SELECT * FROM wishlist WHERE member_id = ?1 AND product_id = ?2 And is_deleted = 0", nativeQuery = true)
    WishList findByMemberIdAndProducIdtAndIsDelete(Integer memberId, Integer productId);




}
