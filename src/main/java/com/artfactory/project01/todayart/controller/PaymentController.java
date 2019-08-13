package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.entity.Payment;
import com.artfactory.project01.todayart.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    /*
    작성자: 국화
    주문 생성과 동시에 결제대기 상태의 결제 record 생성
    @param
    @return
     */
    @RequestMapping
    @PostMapping
    public Payment createPayment(Payment payment, Integer orderId, Integer orderedDetailId, int price){
        return paymentService.createPayment(payment, orderId, orderedDetailId, price);
    }

}
