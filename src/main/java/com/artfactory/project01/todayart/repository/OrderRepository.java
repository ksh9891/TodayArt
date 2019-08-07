package com.artfactory.project01.todayart.repository;

import com.artfactory.project01.todayart.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByMemberId(Integer memberId);

    @Query(value = "SELECT * FROM ordered WHERE member_id = ?1 and order_dated BETWEEN ?2 AND DATE_ADD(?3, INTERVAL 1 DAY)", nativeQuery = true)
    List<Order> findByMemberIdWithTerm(int memberId, Date startDate, Date endDate);

    @Query(value = "SELECT * FROM ordered WHERE order_dated BETWEEN ?1 AND DATE_ADD(?2, INTERVAL 1 DAY)",nativeQuery = true)
    List<Order> findByMemberIdWithTerm(Date startDate, Date endDate);

}
