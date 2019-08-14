package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.Shipping;
import com.artfactory.project01.todayart.model.ShippingForm;
import com.artfactory.project01.todayart.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * 작성자 : 상현
 * 기능 : 배송관련 API Controller
 */
@RestController
@RequestMapping("/shipping")
public class ShippingController {

    @Autowired
    ShippingService shippingService;

    /*
     * 작성자 : 상현
     * 기능 : 전체 배송 조회기능 (관리자)
     * 반환 : List<Shipping>
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Shipping> retrieveShippingList() {
        return shippingService.retrieveShippingList();
    }

    /*
     * 작성자 : 상현
     * 기능 : 배송 정보 생성
     * 인자 : shipping (JSON Object)
     * 반환 : 생성 된 Shipping
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Shipping createShipping(@RequestBody Shipping shipping) {
        return shippingService.createShipping(shipping);
    }

    /*
     * 작성자 : 상현
     * 기능 : 배송 정보 업데이트 ( 배송 준비 중 상태에서 수취자 정보 수정 )
     * 인자 : int id(shipping_id), ShippingForm shippingForm
     * 반환 : 수정 된 Shipping
     */
    @PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Shipping updateShippingBefore(@PathVariable("id") int id, @RequestBody ShippingForm shippingForm) {
        return shippingService.updateShippingBefore(id, shippingForm);
    }

    /*
     * 작성자 : 상현
     * 기능 : 배송 정보 삭제 ( 배송취소(?) 역할을 하는 기능 - 실제 isDelete 값만 변함 )
     * 인자 : int id(shipping_id), ShippingForm shippingForm
     * 반환 : 삭제 된 Shipping
     */
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Shipping deleteShipping(@PathVariable("id") int id, @RequestBody ShippingForm shippingForm) {
        return shippingService.deleteShipping(id, shippingForm);
    }

    /*
     * 작성자 : 상현
     * 기능 : 배송 정보 업데이트 ( 송장번호, 택배사, 배송시작날짜 업데이트 )
     * 인자 : int id(shipping_id), ShippingForm shippingForm
     * 반환 : 변경 된 Shipping
     */

    // Shipping Items 와 함께 트랜잭션처리가 필요함.
    // 현재는 되어 있지 않음
    @PatchMapping(path = "/{id}/tracking")
    public Shipping updateShipping(@PathVariable("id") int id, @RequestBody ShippingForm shippingForm) {
        return shippingService.updateShipping(id, shippingForm);
    }
}
