package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Shipping;
import com.artfactory.project01.todayart.model.ShippingForm;
import com.artfactory.project01.todayart.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShippingService {
    @Autowired
    ShippingRepository shippingRepository;

    public List<Shipping> retrieveShippingList() {
        return shippingRepository.findAll();
    }

    public Shipping createShipping(Shipping shipping) {
        return shippingRepository.save(shipping);
    }

    // 업데이트 로직은 2개가 있어야 한다.
    // 하나는 배송 날짜와, 트래킹 넘버를 추가하는 업데이트
    // 하나는 배송지와 같은 일반적인 내용을 변경하는 업데이트
    // 아래 메서드는 일반적인 내용을 변경하는 업데이트 로직이다.
    //public Shipping updateShipping(int id, Shipping replaceShipping) {
    public Shipping updateShipping(int id, ShippingForm shippingForm) {
        // PATCH
        Shipping shipping = shippingRepository.findById(id).get();
//        shipping.setShippingFee(replaceShipping.getShippingFee());
        Shipping replaceShipping = shippingForm.updateShippingBefore(shipping);
        return shippingRepository.save(replaceShipping);
    }

    public Shipping deleteShipping(int id, int isDelete) {
        // 대충 삭제 로직
        return shippingRepository.save(new Shipping());
    }
}
