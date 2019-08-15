package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.Payment;
import com.artfactory.project01.todayart.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public Payment createPayment(Payment payment, Integer orderId, Integer orderedDetailId, int price){
        return paymentService.createPayment(payment, orderId, orderedDetailId, price);
    }

    /*
      작성자: 국화
      1.paymentMethod에 따라 알맞은 서비스를 호출한다
      2.서비스에서 반환하는 값에 따라 결제성공(true), 결제실패(false)를 return 한다
      @param int
      @return null
    */
    @PatchMapping
    public Boolean updatePayment(Payment payment){
        String paymentMethod = payment.getPayMethod();
        switch(paymentMethod){
            case "카카오페이":
                break;
            case "신용카드":
                break;
            default:
                break;
        }
        return false;
    }

}
