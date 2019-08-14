package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Cart;
import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.entity.Product;
import com.artfactory.project01.todayart.entity.WishList;
import com.artfactory.project01.todayart.repository.ProductRepository;
import com.artfactory.project01.todayart.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WishListService implements Serializable {
    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    ProductRepository productRepository;

    /*
      작성자: 수지
      새로운 wishList 레코드 작성
      @param Integer
      @return null
    */
    public WishList createWishlist(Member member, WishList wishList){
        int productId = wishList.getProduct().getProductId();
        Product product = productRepository.findById(productId).get();
        wishList.setProductPrice(member.getMemberId());
        wishList.setProductPrice(product.getProductPrice());
        wishList.setProductSize(product.getProductSize());
        wishList.setThumbnailId(product.getThumbnailId());
//        wishList.setMemberId(member.getMemberId());

        return wishListRepository.save(wishList);
    }

    /*
      작성자: 수지
      각 회원별 (삭제되지않은) 찜하기 리스트를 보여준다
      @param Member
      @return ArrayList<WishList>
    */
    public ArrayList<WishList> retrieveWishlist(Member member){
        ArrayList<WishList> wishList = wishListRepository.findAllByMemberIdAndIsDeleted(member.getMemberId(), 0);
        return wishList;
    }





//      /*작성자: 국화
//      장바구니에 담긴 아이템의 수량 변경
//      @param Member
//      @param Map<String, ChangedCartItem>
//      @return ArrayList<Cart>*/
//
//    public ArrayList<WishList> updateWishlist(Member member, Map<String, ChangedCartItem> Item){
//        for(String item : Item.keySet()){
//            WishList wishList = wishListRepository.findById(Item.get(item).getCartId()).get();
//            wishList.setQuantity(Item.get(item).getQuantity());
//            wishListRepository.save(wishList);
//        }
//        return retrieveWishlist(member);
//    }

    /*
      작성자: 수지
      찜하기에서 삭제(감추기)
      @param Integer
      @return null
    */
    public void deleteWishlist(int wishlistId){
        WishList wishList = wishListRepository.findById(wishlistId).get();
        wishList.setIsDeleted(1);
        wishListRepository.save(wishList);
    }




}
