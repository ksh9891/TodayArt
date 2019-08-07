package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.entity.Order;
import com.artfactory.project01.todayart.entity.OrderDetail;
import com.artfactory.project01.todayart.model.OrderForm;
import com.artfactory.project01.todayart.model.Period;
import com.artfactory.project01.todayart.service.OrderService;
import com.artfactory.project01.todayart.util.PrincipalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    // 주문 생성
    // wrtie_order 권한필요
    @RequestMapping(method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Order createOrder(@RequestBody OrderForm orderForm, Principal principal){
        Member member = (Member) PrincipalUtil.from(principal);
        orderForm.setMemberId(member.getMemberId());
        return orderService.createOrder(orderForm);
    }

    // (관리자는 모든 주문, 주문자는 자신의) 모든 주문 확인
     @PreAuthorize("hasAnyRole('ADMIN','MEMBER','ARTIST')")
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Order> listOf(Principal principal){
        Member member = (Member) PrincipalUtil.from(principal);
        int id = member.getMemberId();
        int auth =member.getAuthority();
        return ((auth==2)?orderService.getOrders():orderService.getOrdersByUser(id));
    }

    @RequestMapping(path="/period", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Order> listOfOrderWithPeriod(@RequestBody Period period, Principal principal){
        Member member = (Member) PrincipalUtil.from(principal);
        int auth =member.getAuthority();
        if(auth==2){
            return orderService.getOrdersByUserWithTerm(period);
        }
        int id = member.getMemberId();
        return orderService.getOrdersByUserWithTerm(id, period);

    }

    // (관리자)(특정 id) 주문 확인 // 소비자 본인(의 특정) 주문 확인
    @RequestMapping(path="/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Order retrieveOrder(@PathVariable("id") int orderId, Principal principal){
        Member member = (Member) PrincipalUtil.from(principal);
        int auth =member.getAuthority();
        return ((auth==2)?orderService.getOrderForAdmin(orderId):orderService.getOrder(orderId, member));
    }


    // (관리자)(특정 id) 주문(디테일) 확인 // 소비자 본인(의 특정) 주문(디테일) 확인
    @RequestMapping(path="/{id}/detail", method = RequestMethod.GET, produces ={MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<OrderDetail> retrieveOrderDetail(@PathVariable("id") int orderId, Principal principal){
        Member member = (Member) PrincipalUtil.from(principal);
        int auth =member.getAuthority();
        return ((auth==2)?orderService.getOrderDetailForAdmin(orderId):orderService.getOrderDetail(orderId, member));
    }




    // (관리자)(특정 유저의) 주문 확인
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path="/user/{memberId}", method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Order> listOfOrderByMember(@PathVariable("memberId") int memberId){
        return orderService.getOrdersByUser(memberId);
    }







    @RequestMapping(path="/{id}/{price}", method=RequestMethod.PATCH, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Order patchOrder(@PathVariable("id") Integer orderId, @PathVariable("price") Integer price){
        return orderService.patchOrder(orderId, price);
    }

}
