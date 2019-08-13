package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.Ordered;
import com.artfactory.project01.todayart.entity.OrderedDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class OrderForm {
    @Getter
    @Setter
    private ArrayList<Integer> cartIdList;

    @Getter
    @Setter
    private int shippingFee;

    @Getter
    @Setter
    private int totalPrice;

}
