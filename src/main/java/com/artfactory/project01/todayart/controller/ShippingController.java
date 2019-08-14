package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.Shipping;
import com.artfactory.project01.todayart.model.ShippingForm;
import com.artfactory.project01.todayart.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    @Autowired
    ShippingService shippingService;

    @GetMapping(path = "/ship", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Shipping> retrieveShippingList() {
        return shippingService.retrieveShippingList();
    }

    @PostMapping(path = "/ship", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Shipping createShipping(@RequestBody Shipping shipping) {
        return shippingService.createShipping(shipping);
    }

    @PatchMapping(path = "/ship/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    //public Shipping updateShipping(@PathVariable("id") int id, @RequestBody Shipping shipping) {
    public Shipping updateShipping(@PathVariable("id") int id, @RequestBody ShippingForm shippingForm) {
        return shippingService.updateShipping(id, shippingForm);
    }

    @DeleteMapping(path = "/ship/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Shipping deleteShipping(@PathVariable("id") int id, @RequestParam int isDelete) {
        return shippingService.deleteShipping(id, isDelete);
    }
}
