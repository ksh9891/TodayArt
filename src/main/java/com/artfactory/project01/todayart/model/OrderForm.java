package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.Ordered;
import com.artfactory.project01.todayart.entity.OrderedDetail;

import java.util.List;

public class OrderForm {
    private int memberId;
    private Integer cartId;
    private int totalPrice;
    private Integer shippingFee;
    private List<OrderedDetail> orderDetail;


    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(int shippingFee) {
        this.shippingFee = shippingFee;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<OrderedDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderedDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Ordered setOrder(){
        Ordered ordered = new Ordered();
        ordered.setMemberId(memberId);
        ordered.setTotalPrice(totalPrice);
        ordered.setShippingFee(shippingFee);
        return ordered;
    }
}
