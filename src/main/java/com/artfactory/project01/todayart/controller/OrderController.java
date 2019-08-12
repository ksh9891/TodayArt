package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.entity.Ordered;
import com.artfactory.project01.todayart.entity.OrderedDetail;
import com.artfactory.project01.todayart.model.ChangeOrderDetail;
import com.artfactory.project01.todayart.model.OrderForm;
import com.artfactory.project01.todayart.model.OrderFormReturn;
import com.artfactory.project01.todayart.model.Period;
import com.artfactory.project01.todayart.service.OrderService;
import com.artfactory.project01.todayart.util.PrincipalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    private Member member;
    private static Member getMember(Principal principal){
        return (Member) PrincipalUtil.from(principal);
    }


    // POST =========================================

    // 주문 생성
    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST')")
    @RequestMapping(method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Ordered createOrder(@RequestBody OrderForm orderForm, Principal principal){
        member = getMember(principal);
        orderForm.setMemberId(member.getMemberId());
        return orderService.createOrder(orderForm);
    }

    // GET =========================================

    // (관리자는 모든 주문, 주문자는 자신의) 모든 주문 확인
    // - 이름/가격/날짜/배송정보/주문상태
    //  주문 아이템 상태로 조회
    // 결제완료, 배송준비, 배송중, 배송완료, 주문확정, 주문취소, 반품, 환불
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Ordered> listOf(@RequestParam(value = "type", required = false)String type, Principal principal){
        member = getMember(principal);
        int id = member.getMemberId();
        String role = member.getRole();
        if(type==null){
            return ((role.equals("ROLE_ADMIN"))?orderService.getOrders():orderService.getOrdersByUser(id));}
        else{

            return orderService.getOrdersByStatus(role, id, type);
        }
    }

    @RequestMapping(path="/period", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ArrayList<Ordered> listOfOrderWithPeriod(@RequestBody Period period, Principal principal){
        member = getMember(principal);
        String role = member.getRole();
        if(role.equals("ROLE_ADMIN")){
            return orderService.getOrdersWithTerm(period);
        }
        int id = member.getMemberId();
        return orderService.getOrdersByUserWithTerm(id, period);

    }




    // (관리자)(특정 id) 주문(디테일) 확인 // 소비자 본인(의 특정) 주문(디테일) 확인
    // -주문일자/주문번호/(영수증 발급내역)/상품주문번호/상품정보/상품금액(수량)/배송비/판매자/주문진행상태/주문(상품)금액
    @RequestMapping(path="/{id}/detail", method = RequestMethod.GET, produces ={MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<OrderedDetail> retrieveOrderDetail(@PathVariable("id") int orderId, Principal principal){
        member = getMember(principal);
        String role = member.getRole();
        return ((role.equals("ROLE_ADMIN"))?orderService.getOrderDetailForAdmin(orderId):orderService.getOrderDetail(orderId, member));
    }




    // (관리자)(특정 유저의) 주문 확인
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path="/user/{memberId}", method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Ordered> listOfOrderByMember(@PathVariable("memberId") int memberId){
        return orderService.getOrdersByMemberId(memberId);
    }



    // PATCH =========================================


    // 주문자 : 상태변경 (주문취소)
    // 판매자 : 상태변경 (배송준비중=>배송중/tracking_number 입력)
    // 판매자 : 상태변경 (배송준비중=>주문취소 : 취소사유 입력)

    @RequestMapping(method=RequestMethod.PATCH, produces ={MediaType.APPLICATION_JSON_UTF8_VALUE})
    public OrderedDetail changeStatus(@RequestBody ChangeOrderDetail changeList,
                                Principal principal){
        member = getMember(principal);
        return orderService.changeStatus(member, changeList);

    }


    // DELETE =========================================

    // 주문내역 감추기(실제 삭제 X)
    @RequestMapping(path="/{id}", method=RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public void deleteOrder(@PathVariable("id") int id){
        orderService.hiddenOrders(id);

    }
}
