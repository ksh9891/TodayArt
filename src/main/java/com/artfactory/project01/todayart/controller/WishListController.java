package com.artfactory.project01.todayart.controller;


import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.entity.Product;
import com.artfactory.project01.todayart.entity.WishList;
import com.artfactory.project01.todayart.repository.ProductRepository;
import com.artfactory.project01.todayart.service.WishListService;
import com.artfactory.project01.todayart.util.PrincipalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/wish")
public class WishListController {

    @Autowired
    WishListService wishListService;
    private Member member;
    private static Member getMember(Principal principal){
        return (Member) PrincipalUtil.from(principal);
    }

    /*
    작성자: 수지
    찜하기(하트) 눌렀을 때 찜하기에 상품 등록
    @Param WishList
    @Return WishList
    */
    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST')")
    @RequestMapping(method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public WishList createWishlist(@RequestBody WishList wishList, Principal principal){
        member = getMember(principal);
        return wishListService.createWishlist(member, wishList);
    }


    /*
   작성자: 수지
   찜하기를 눌렀을 때 찜하기에 들어가있는 상품을 보여준다
   @Param
   @Return ArrayList<WishList>
   */
    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST')")
    @RequestMapping(method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ArrayList<WishList> retrieveWishlist(Principal principal){
        member = getMember(principal);
        return wishListService.retrieveWishlist(member);
    }

//    /*
//   작성자: 국화
//   장바구니에 담긴 아이템 수량변경
//   @param Map<String, changeCartItem>
//   @param Principal
//   @return ArrayList<Cart>
//   */
//    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST')")
//    @RequestMapping(method = RequestMethod.PATCH, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    public ArrayList<Cart> updateCart(@RequestBody Map<String, ChangedCartItem> changedCartItem, Principal principal){
//        member = getMember(principal);
//        return wishListService.updateWishlist(member,changedCartItem);
//    }

//    /*
//   작성자: 수지
//   찜하기에서 장바구니로 이동
//   @Param WishList
//   @Return WishList
//   */
//    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST')")
//    @RequestMapping(method = RequestMethod.POST,
//            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    public WishList createWishlistToCart(@RequestBody WishList wishList, Principal principal){
//        member = getMember(principal);
//        return wishListService.createWishlistToCart(member, wishList);
//    }


    /*
   작성자: 수지
   찜하기에 담긴 상품 삭제
   @param Integer
   @return null
   */
    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST')")
    @RequestMapping(path="/{wishlistId}",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public void deleteWishlist(@PathVariable("id")Integer wishlistId){
        wishListService.deleteWishlist(wishlistId);

    }

}
