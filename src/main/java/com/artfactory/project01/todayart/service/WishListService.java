package com.artfactory.project01.todayart.service;



import com.artfactory.project01.todayart.entity.Cart;
import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.entity.Product;
import com.artfactory.project01.todayart.entity.WishList;
import com.artfactory.project01.todayart.model.ProductForm;
import com.artfactory.project01.todayart.repository.CartRepository;
import com.artfactory.project01.todayart.repository.ProductRepository;
import com.artfactory.project01.todayart.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class WishListService {

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;



    /*
      작성자: 채경
      새로운 wishList 레코드 작성
    */
    @Transactional
    public WishList createWishList(Member member, WishList wishList){
        int productId = wishList.getProduct().getProductId();
        Product product = productRepository.findById(productId).get();
        // 찜하기 클릭했을 때 프로덕트의 count 컬럼 1 증가
        ProductForm productForm = new ProductForm();
        productForm.setProduct(product);
        product.setCountWishlist(product.getCountWishlist()+1);
        //
        wishList.setMemberId(member.getMemberId());
        wishList.setProductPrice(product.getProductPrice());
        wishList.setProductSize(product.getProductSize());
        wishList.setThumbnailId(product.getThumbnailId());
        wishList.setArtistName(product.getArtistName());
        wishList.setIsStock(product.getRemain());

        productRepository.save(product);

        return wishListRepository.save(wishList);
    }





    /*
      작성자: 채경
      각 회원의 (삭제되지않은) 찜하기 목록 불러오기
      @param Member
      @return ArrayList<WishList>
    */
    @Transactional
    public ArrayList<WishList> retrieveWishList(Member member){
        ArrayList<WishList> wishLists =
                wishListRepository.findAllByMemberIdAndIsDelete(member.getMemberId(), 0);
        return wishLists;
    }



    /*
     작성자: 채경
     기능 : 찜하기에서 장바구니로 이동시켜줌
     @param int
     @return null
   */
    @Transactional
    public Cart createWishToCart(Integer wishListId){
        WishList wishList = wishListRepository.findByWishlistIdAndIsDelete(wishListId, 0);
        Cart cart = new Cart();
        int productId = wishList.getProduct().getProductId();
        Product product = productRepository.findById(productId).get();
        cart.setMemberId(wishList.getMemberId());
        cart.setProduct(wishList.getProduct());
        cart.setProductPrice(wishList.getProductPrice());
        cart.setProductSize(wishList.getProductSize());
        cart.setShippingFee(product.getShippingFee());
        cart.setIsStock(wishList.getIsStock());
        cart.setQuantity(1);
        return cartRepository.save(cart);
//        
    }


    /*
     작성자: 채경
     찜하기에서 삭제(감추기)
     @param int
     @return null
   */
    @Transactional
    public void deleteWishList(Integer wishListId){
        WishList wishList = wishListRepository.findById(wishListId).get();
        wishList.setIsDelete(1);
        wishListRepository.save(wishList);
    }

}