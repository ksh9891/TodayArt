package com.artfactory.project01.todayart.repository;


import com.artfactory.project01.todayart.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {


        ArrayList<WishList> findAllByMemberIdAndIsDelete(int memberId, int isDelete);

        WishList findByWishlistIdAndIsDelete(Integer wishListId, Integer isDelete);


}