package com.artfactory.project01.todayart.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class ChangedCartItem implements Serializable {

    private int cartId;

    @Getter
    @Setter
    private int quantity;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
}
