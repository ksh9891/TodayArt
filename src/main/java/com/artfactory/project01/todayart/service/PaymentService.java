package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.OrderedDetail;
import com.artfactory.project01.todayart.entity.Payment;
import com.artfactory.project01.todayart.entity.KakaoInfoRequest;
import com.artfactory.project01.todayart.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    /**
     * 작성 : 국화
     * 카카오페이 결제 승인 후 DB payment Table 에 결제정보를 채워넣기 위한 함수
     * @param list
     * @param kakao
     * @return
     * @throws Exception
     */
    public Boolean updatePaymentForKakao(List<OrderedDetail> list, KakaoInfoRequest kakao) throws Exception{
        int orderedId;
        int orderedDetailId;
        Payment payment;
        try {
            for (OrderedDetail item : list) {
                orderedId = item.getOrderId();
                orderedDetailId = item.getOrderDetailId();
                payment = paymentRepository.findByOrderIdAndOrderedDetailId(orderedId, orderedDetailId);
                payment.setStatus("결제완료");
                payment.setPayMethod("카카오페이");
                payment.setOrderDetailPrice(item.getTotalPrice());
                payment.setTotalOrderPrice(kakao.getAmount().getTotal());
                payment.setPgNumber(kakao.getTid());
                payment.setPayDated(kakao.getApproved_at());
                paymentRepository.save(payment);
            }
            return true;
        }catch(Exception e){
            throw new Exception();
        }

    }
}
