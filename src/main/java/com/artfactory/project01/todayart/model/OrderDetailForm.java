package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.OrderedDetail;

public class OrderDetailForm {
    private int productId;
    private int quantity;
    private int productPrice;

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

    public OrderedDetail setOrderDetail(int orderId){
        OrderedDetail orderedDetail = new OrderedDetail();
        orderedDetail.setProductPrice(productPrice);
        orderedDetail.setQuantity(quantity);
        orderedDetail.setTotalPrice(productPrice*quantity);
        return orderedDetail;
    }
}
