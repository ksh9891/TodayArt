package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Shipping;
import com.artfactory.project01.todayart.model.ShippingForm;
import com.artfactory.project01.todayart.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Shipping updateShipping(int id, ShippingForm shippingForm) {
        // 대충 업데이트 로직
        return shippingRepository.save(new Shipping());
        // return shippingRepository.save();
    }

    public Shipping deleteShipping(int id, int isDelete) {
        // 대충 삭제 로직
        return shippingRepository.save(new Shipping());
    }
}
