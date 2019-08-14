package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.Shipping;
import com.artfactory.project01.todayart.model.ShippingForm;
import com.artfactory.project01.todayart.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    @Autowired
    ShippingService shippingService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Shipping> retrieveShippingList() {
        return shippingService.retrieveShippingList();
    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Shipping createShipping(@RequestBody Shipping shipping) {
        return shippingService.createShipping(shipping);
    }

    // 배송 준비 중 상태에서 주소정보 수정
    @PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Shipping updateShippingBefore(@PathVariable("id") int id, @RequestBody ShippingForm shippingForm) {
        return shippingService.updateShippingBefore(id, shippingForm);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Shipping deleteShipping(@PathVariable("id") int id, @RequestBody ShippingForm shippingForm) {
        return shippingService.deleteShipping(id, shippingForm);
    }

    // 배송 준비중 -> 배송 중
    @PatchMapping(path = "/{id}/tracking")
    public Shipping updateShipping(@PathVariable("id") int id, @RequestBody ShippingForm shippingForm) {
        return shippingService.updateShipping(id, shippingForm);
    }
}
