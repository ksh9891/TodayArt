package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Cart;
import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.entity.Product;
import com.artfactory.project01.todayart.entity.WishList;
import com.artfactory.project01.todayart.model.ChangedCartItem;
import com.artfactory.project01.todayart.model.ProductForm;
import com.artfactory.project01.todayart.repository.CartRepository;
import com.artfactory.project01.todayart.repository.ProductRepository;
import com.artfactory.project01.todayart.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartService implements Serializable {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    WishListRepository wishListRepository;

    /*
      작성자: 국화 // 190815 채경 수정
      새로운 cart 레코드 작성 // 카트에 추가 시 중복체크 기능 추가
      @param int
      @return null
    */
    @Transactional
    public Cart createCart(Member member, Cart cart){
        int productId = cart.getProduct().getProductId();
        Product product = productRepository.findById(productId).get();
        Integer memberId = member.getMemberId();
        if (cartRepository.findByMemberIdAndProducIdtAndIsDelete(memberId, productId) != null) {
            System.out.println("중복");
        }else if(cartRepository.findByMemberIdAndProducIdtAndIsDelete(memberId, productId) == null){
            // 찜하기 클릭했을 때 프로덕트의 count 컬럼 1 증가
            ProductForm productForm = new ProductForm();
            productForm.setProduct(product);
            product.setCountCart(product.getCountCart()+1);
            //
            cart.setProductPrice(product.getProductPrice());
            cart.setProductSize(product.getProductSize());
            cart.setMemberId(member.getMemberId());
            productRepository.save(product);
            cart = cartRepository.save(cart);
        }

        return cart;

    }



    /*
 작성자: 채경
 기능 : 찜하기에서 장바구니로 이동시켜줌
 @param int
 @return null
*/
    @Transactional
    public Cart createWishToCart(Member member, Integer wishListId){
        WishList wishList = wishListRepository.findById(wishListId).get();
        int productId = wishList.getProduct().getProductId();
        Product product = productRepository.findById(productId).get();

        Cart cart = new Cart();
        cart.setProduct(wishList.getProduct());
        cart.setProductPrice(product.getProductPrice());
        cart.setProductSize(product.getProductSize());
        cart.setMemberId(member.getMemberId());
        cart.setProductPrice(product.getProductPrice());
        cart.setProductSize(product.getProductSize());
        cart.setShippingFee(product.getShippingFee());
        cart.setIsStock(wishList.getIsStock());
        cart.setQuantity(1);
        // 장바구니로 이동 클릭했을 때 프로덕트의 count 컬럼 1 증가
        ProductForm productForm = new ProductForm();
        productForm.setProduct(product);
        product.setCountCart(product.getCountCart()+1);
        //
        productRepository.save(product);
        wishList.setIsDelete(1);
        return cartRepository.save(cart);
    }



    /*
      작성자: 국화
      각 회원의 (삭제되지않은) 장바구니 목록 불러오기
      @param Member
      @return ArrayList<Cart>
    */
    @Transactional
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
    @Transactional
    public ArrayList<Cart> updateCart(Member member, Map<String, ChangedCartItem> Item){
        for(String item : Item.keySet()){
            Cart cart = cartRepository.findById(Item.get(item).getCartId()).get();
            cart.setQuantity(Item.get(item).getQuantity());
            cartRepository.save(cart);
        }
        return retrieveCart(member);
    }

    /*
    작성자: 채경
    기능 : 찜하기에서 장바구니로 이동시켜줌
    @param int
    @return null
  */
    @Transactional
    public Cart createWishToCart(Member member, Integer wishListId){
        WishList wishList = wishListRepository.findById(wishListId).get();
        Cart cart = new Cart();
        int productId = wishList.getProduct().getProductId();
        Product product = productRepository.findById(productId).get();
        cart.setProduct(wishList.getProduct());
        cart.setMemberId(member.getMemberId());
        cart.setProductPrice(product.getProductPrice());
        cart.setProductSize(product.getProductSize());
        cart.setShippingFee(product.getShippingFee());
        cart.setIsStock(wishList.getIsStock());
        cart.setQuantity(1);
        // 찜하기 클릭했을 때 프로덕트의 count 컬럼 1 증가
        ProductForm productForm = new ProductForm();
        productForm.setProduct(product);
        product.setCountCart(product.getCountCart()+1);

        //


        //

        productRepository.save(product);
        wishList.setIsDelete(1);
        return cartRepository.save(cart);


    }


    /*
      작성자: 국화
      장바구니에서 삭제(감추기)
      @param int
      @return null
    */
    @Transactional
    public void deleteCart(int cartId){
        Cart cart = cartRepository.findById(cartId).get();
        cart.setIsDeleted(1);
        cartRepository.save(cart);
    }


}
