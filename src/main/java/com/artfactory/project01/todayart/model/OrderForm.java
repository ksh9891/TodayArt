package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.Payment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderForm {

    private List<Integer> cartIdList;

    private int shippingFee;

    private int totalPrice;

    private Payment payment;

}
