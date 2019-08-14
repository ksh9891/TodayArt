package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.entity.Payment;
import com.artfactory.project01.todayart.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;


    /*
      작성자: 국화
      빈 payment 레코드를 생성한다
      @param Payment
      @param int
      @param int
      @return Payment
    */
    public Payment createPayment(Payment payment, int orderId, int orderedDetailId, int price){
        Payment newPayment = new Payment().setPayment(payment);
        newPayment.setOrderId(orderId);
        newPayment.setOrderedDetailId(orderedDetailId);
        newPayment.setOrderDetailPrice(price);

        return paymentRepository.save(newPayment);
    }

    /*
      작성자: 국화
      카카오페이 api 호출
      @param
      @return null
    */
    public Boolean callKakaoPay(){
        return false;
    }

    /*
      작성자: 국화
      신용카드 api 호출
      @param
      @return null
    */
    public Boolean callCreditCard(){
        return false;
    }
}
