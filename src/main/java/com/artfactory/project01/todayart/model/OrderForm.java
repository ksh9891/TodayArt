package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.Order;
import com.artfactory.project01.todayart.entity.OrderDetail;
import org.springframework.lang.Nullable;

import java.util.List;

public class OrderForm {
    private int memberId;
    private int totalPrice;
    private Integer shippingFee;
    private Integer cartId;
    private List<OrderDetailForm> orderDetail;


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

    public List<OrderDetailForm> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetailForm> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Order setOrder(){
        Order order = new Order();
        order.setMemberId(memberId);
        order.setTotalPrice(totalPrice);
        order.setShippingFee(shippingFee);
        order.setCartId(cartId);
        return order;
    }
}
