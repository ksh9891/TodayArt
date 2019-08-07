package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.OrderDetail;

public class OrderDetailForm {
    private int productId;
    private int productPrice;
    private int quantity;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderDetail setOrderDetail(int orderId){
        OrderDetail orderDetail= new OrderDetail();
        orderDetail.setOrderDetailId(orderId, productId);
        orderDetail.setProductPrice(productPrice);
        orderDetail.setQuantity(quantity);
        orderDetail.setTotalPrice(productPrice*quantity);
        return orderDetail;
    }
}
