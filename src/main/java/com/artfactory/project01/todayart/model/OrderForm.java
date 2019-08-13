package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.Ordered;
import com.artfactory.project01.todayart.entity.OrderedDetail;
import com.artfactory.project01.todayart.entity.Payment;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class OrderForm {

    private ArrayList<Integer> cartIdList;

    private int shippingFee;

    private int totalPrice;

    private Payment payment;

}
