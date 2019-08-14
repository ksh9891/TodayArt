package com.artfactory.project01.todayart.repository;

import com.artfactory.project01.todayart.entity.Product;
import com.artfactory.project01.todayart.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface  WishListRepository extends JpaRepository<WishList, Integer> {

        ArrayList<WishList> findAllByMemberIdAndIsDeleted(int memberId, int isDeleted);

//        List<WishList> findAllByMemberIdAndIsDelete(int memberId, Integer isDelete);

}
