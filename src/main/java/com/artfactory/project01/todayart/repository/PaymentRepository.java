package com.artfactory.project01.todayart.repository;

import com.artfactory.project01.todayart.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findByOrderIdAndOrderedDetailId(int orderId, int orderedDetailId);
}
