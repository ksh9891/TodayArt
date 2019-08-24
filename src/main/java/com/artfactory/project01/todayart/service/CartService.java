package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.*;
import com.artfactory.project01.todayart.model.ChangedCartItem;
import com.artfactory.project01.todayart.model.ProductForm;
import com.artfactory.project01.todayart.repository.CartRepository;
import com.artfactory.project01.todayart.repository.ProductRepository;
import com.artfactory.project01.todayart.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
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

    /*
      작성자: 국화
      // -> 190815 채경 수정
      // 새로운 cart 레코드 작성 후 불러옴
      // 카트에 추가 시 중복체크 기능 추가
      // -> 190824 DB 입력을 다른 메소드로 분리함
      @param int
      @return null
    */
    public Cart createCart(Member member, Cart cart){
        Product product = cart.getProduct();
        int productId = product.getProductId();
        int quantity = cart.getQuantity();
        Integer memberId = member.getMemberId();

        writeCartToDB(product, memberId, quantity);
        cart = cartRepository.findByMemberIdAndProductIdtAndIsDelete(memberId, productId);
        return cart;
    }

    /**
     * 작성자: 국화
     * createCart를 하기 위해 중복을 체크하고, DB에 실제 데이터를 입력하는 단계
     * @param product
     * @param memberId
     * @param quantity
     */
    @Transactional
    public void writeCartToDB(Product product, int memberId, int quantity){
        Cart cart = cartRepository.findByMemberIdAndProductIdtAndIsDelete(memberId, product.getProductId());
        if (cart != null) {
            cart.setQuantity(cart.getQuantity()+quantity);
            cartRepository.save(cart);
        }else if(cart == null){
            // 찜하기 클릭했을 때 프로덕트의 count 컬럼 1 증가
            ProductForm productForm = new ProductForm();
            productForm.setProduct(product);
            product.setCountCart(product.getCountCart()==null?1:+product.getCountCart()+1);
            product.setCountWishlist(product.getCountWishlist()==null?0:product.getCountWishlist());
            product.setRemain(product.getRemain()==null?0:product.getRemain());
            product.setShippingFee(product.getShippingFee()==null?0:product.getShippingFee());

            cart = new Cart();
            cart.setQuantity(quantity);
            cart.setProduct(product);
            cart.setProductPrice(product.getProductPrice());
            cart.setProductSize(product.getProductSize());
            cart.setShippingFee(product.getShippingFee());
            cart.setMemberId(memberId);
            productRepository.save(product);
            cartRepository.save(cart);
        }
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
        wishList.setIsDelete("y");
        return cartRepository.save(cart);
    }



    /*
      작성자: 국화
      각 회원의 (삭제되지않은) 장바구니 목록 불러오기
      @param Member
      @return ArrayList<Cart>
    */
    @Transactional
    public List<Cart> retrieveCart(Member member){
        ArrayList<Cart> cartList = cartRepository.findAllByMemberIdAndIsDeleted(member.getMemberId(), "n");
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
    public List<Cart> updateCart(Member member, Map<String, ChangedCartItem> Item){
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
    @Transactional
    public boolean deleteCart(int cartId){
        Cart cart = cartRepository.findById(cartId).get();
        cart.setIsDeleted("y");
        cartRepository.save(cart);
        return true;
    }

    public boolean deleteCart(List<OrderedDetail> list) throws Exception{
        try {
            for (OrderedDetail item : list) {
                deleteCart(item.getCartId());
            }
            return true;
        }catch(Exception ex){
            throw new Exception();
        }
    }


}
