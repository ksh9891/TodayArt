package com.artfactory.project01.todayart.repository;

import com.artfactory.project01.todayart.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetail.OrderDetailId> {

    @Query(value = "select * from order_detail where order_id = ?1", nativeQuery = true)
    List<OrderDetail> findAllByOrderId(int orderId);
}
