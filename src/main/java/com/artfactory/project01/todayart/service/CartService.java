package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Cart;
import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.entity.Product;
import com.artfactory.project01.todayart.model.ChangedCartItem;
import com.artfactory.project01.todayart.repository.CartRepository;
import com.artfactory.project01.todayart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartService implements Serializable {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    /*
      작성자: 국화
      새로운 cart 레코드 작성
      @param int
      @return null
    */
    public Cart createCart(Member member, Cart cart){
        int productId = cart.getProduct().getProductId();
        Product product = productRepository.findById(productId).get();
        cart.setProductPrice(product.getProductPrice());
        cart.setProductSize(product.getProductSize());
        cart.setMemberId(member.getMemberId());
        return cartRepository.save(cart);
    }

    /*
      작성자: 국화
      각 회원의 (삭제되지않은) 장바구니 목록 불러오기
      @param Member
      @return ArrayList<Cart>
    */
    public ArrayList<Cart> retrieveCart(Member member){
        ArrayList<Cart> cartList = cartRepository.findAllByMemberIdAndIsDeleted(member.getMemberId(), 0);
        return cartList;
    }


    /*
      작성자: 국화
      장바구니에 담긴 아이템의 수량 변경
      @param Member
      @param Map<String, ChangedCartItem>
      @return ArrayList<Cart>
    */
    public ArrayList<Cart> updateCart(Member member, Map<String, ChangedCartItem> Item){
        for(String item : Item.keySet()){
            Cart cart = cartRepository.findById(Item.get(item).getCartId()).get();
            cart.setQuantity(Item.get(item).getQuantity());
            cartRepository.save(cart);
        }
        return retrieveCart(member);
    }
    /*
      작성자: 국화
      장바구니에서 삭제(감추기)
      @param int
      @return null
    */
    public void deleteCart(int cartId){
        Cart cart = cartRepository.findById(cartId).get();
        cart.setIsDeleted(1);
        cartRepository.save(cart);
    }


}
