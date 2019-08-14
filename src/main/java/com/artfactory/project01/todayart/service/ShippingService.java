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

    public Shipping updateShippingBefore(int id, ShippingForm shippingForm) {
        Shipping shipping = shippingRepository.findById(id).get();
        return shippingRepository.save(shippingForm.updateShippingBefore(shipping));
    }

    public Shipping deleteShipping(int id, ShippingForm shippingForm) {
        Shipping shipping = shippingRepository.findById(id).get();
        return shippingRepository.save(shippingForm.deleteShipping(shipping));
    }

    public Shipping updateShipping(int id, ShippingForm shippingForm) {
        Shipping shipping = shippingRepository.findById(id).get();
        return shippingRepository.save(shippingForm.updateShipping(shipping));
    }
}
