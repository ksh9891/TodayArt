package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.Cart;
import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.model.ChangedCartItem;
import com.artfactory.project01.todayart.service.CartService;
import com.artfactory.project01.todayart.util.PrincipalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;
    private Member member;
    private static Member getMember(Principal principal){
        return (Member) PrincipalUtil.from(principal);
    }

    /*
    작성자: 국화
    기능 : 장바구니에 담기 눌렀을 때 장바구니에 아이템 생성
    입력 : Cart Entity
    출력 : Save 된 Cart 객체
    */
    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST')")
    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Cart enrollCart(@RequestBody Cart cart, Principal principal){
        member = getMember(principal);
        return cartService.enrollCart(member, cart);
    }

     /*
    작성자: 국화
    기능 : 장바구니를 클릭했을 때 장바구니에 담겨있는 아이템이 보인다
    입력 : Member
    출력 : Member Id 로 검색 된 ArrayList<Cart>
    */
    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST')")
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ArrayList<Cart> retrieveCart(Principal principal){
        member = getMember(principal);
        return cartService.retrieveCart(member);
    }

    /*
   작성자: 국화
   기능 : 장바구니에 담긴 아이템 수량변경
   입력 : Member, cart
   출력 : Member Id 로 검색 된 ArrayList<Cart>
   */
    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST')")
    @RequestMapping(method = RequestMethod.PATCH, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ArrayList<Cart> updateCart(@RequestBody Map<String, ChangedCartItem> changedCartItem, Principal principal){
        member = getMember(principal);
        return cartService.updateCart(member,changedCartItem);
    }

    /*
   작성자: 국화
   기능 : 장바구니에 담긴 아이템 삭제
   입력 : cartId
   출력 : -
   */
    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST')")
    @RequestMapping(path="/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public void deleteCart(@PathVariable("id")int cartId){
        cartService.deleteCart(cartId);

    }

}
