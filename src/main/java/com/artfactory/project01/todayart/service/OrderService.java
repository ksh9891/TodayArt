package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.entity.Ordered;
import com.artfactory.project01.todayart.entity.OrderedDetail;
import com.artfactory.project01.todayart.model.ChangeOrderDetail;
import com.artfactory.project01.todayart.model.OrderForm;
import com.artfactory.project01.todayart.model.OrderFormReturn;
import com.artfactory.project01.todayart.model.Period;
import com.artfactory.project01.todayart.repository.OrderedDetailRepository;
import com.artfactory.project01.todayart.repository.OrderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderedRepository orderedRepository;
    @Autowired
    OrderedDetailRepository orderedDetailRepository;

    // POST =========================================

    @Transactional
    public Ordered createOrder(OrderForm orderForm){

        Ordered ordered = orderedRepository.save(orderForm.setOrder());

        List<OrderedDetail> orderDetailList = orderForm.getOrderDetail();
        Integer orderId = ordered.getOrderId();

        for(OrderedDetail item : orderForm.getOrderDetail()){
            item.setOrderId(orderId);
            item.setTotalPrice(item.getProductPrice()*item.getQuantity());
            orderedDetailRepository.save(item);
        }
        return ordered;
    }

    // GET =========================================

    // 주문 조회
    @Transactional
    public List<Ordered> getOrders(){
        List<Ordered> orders = orderedRepository.findAll();
        return orders;
    }

    @Transactional
    public List<Ordered> getOrdersByUser(int id){
        List<Ordered> orders = orderedRepository.findByMemberId(id);
        return orders;
    }




    @Transactional
    public ArrayList<Ordered> getOrdersWithTerm(Period period){
        Date startDate = period.getStartDate();
        Date endDate = period.getEndDate();
        ArrayList<Ordered> orderedList = orderedRepository.findByMemberIdWithTerm(startDate, endDate);
        return orderedList;
    }


    @Transactional
    public ArrayList<Ordered> getOrdersByUserWithTerm(int id, Period period){
        Date startDate = period.getStartDate();
        Date endDate = period.getEndDate();
        ArrayList<Ordered> orderedList = orderedRepository.findByMemberIdWithTerm(id, startDate, endDate);
        return orderedList;
    }



    @Transactional
    public List<Ordered> getOrdersByMemberId(int memberId){
        return orderedRepository.findByMemberId(memberId);
    }


    /*
    @Transactional
    public Ordered getOrder(int orderId, Member member){
        Ordered ordered = orderedRepository.findById(orderId).get();
        int memberId = member.getMemberId();
        if(ordered.getMemberId()==memberId){
            return ordered;
        }
        return null;
    }

    @Transactional
    public Ordered getOrderForAdmin(int orderId){
        return orderedRepository.findById(orderId).get();

    }
    */

    // 구매자용 특정 주문정보디테일 보기
    @Transactional
    public List<OrderedDetail> getOrderDetail(int orderId, Member member){
        int memberId = member.getMemberId();
        Ordered order = orderedRepository.findByOrderIdAndMemberId(orderId,memberId);
        if(order!=null){
            List<OrderedDetail> orderedDetailList = orderedDetailRepository.findAllByOrderId(orderId);
            return orderedDetailList;
        }else{
            return null;
        }
    }
    // 관리자용 특정 주문 정보 디테일 보기
    @Transactional
    public List<OrderedDetail> getOrderDetailForAdmin(int orderId){

       // Optional<Ordered> order = orderedRepository.findById(orderId);
            List<OrderedDetail> orderedDetailList = orderedDetailRepository.findAllByOrderId(orderId);
            return orderedDetailList;

    }

    @Transactional
    public List<Ordered> getOrdersByStatus(String role, int memberId, String status){
        List<Ordered> orderedList;

        if(role.equals("ROLE_ADMIN")){
            orderedList = orderedRepository.findAllByOrderDetails_Status(status);
        }
        else {
            orderedList = orderedRepository.findAllByOrderDetails_StatusAndMemberId(status, memberId);
        }
        return orderedList;
    }

    // PATCH =========================================

    @Transactional
    public Ordered patchOrder(Integer orderId, Integer shippingfee){
        Ordered ordered = orderedRepository.findById(orderId).get();
        ordered.setShippingFee(shippingfee);
        return orderedRepository.save(ordered);
    }

    @Transactional
    public OrderedDetail changeStatus(Member member, ChangeOrderDetail item){
        String code = item.getChangeCode();
        OrderedDetail orderedDetail = orderedDetailRepository.findByOrderDetailId(item.getOrderDetailId());

        if(code.equals("CUSTOMER")){
            int memberId = member.getMemberId();
            int orderId = orderedDetail.getOrderId();
            Ordered ordered = orderedRepository.findByOrderId(orderId);
            if(memberId==ordered.getMemberId()){
                if(orderedDetail.getStatus().equals("배송준비")){
                    orderedDetail.setStatus("주문취소");
                    return orderedDetailRepository.save(orderedDetail);
                }
            }
            return null;
        }
        else if(code.equals("SELLER")){
            if(item.getStatus().equals("배송중")){
                orderedDetail.setStatus(item.getStatus());
                orderedDetail.setTrackingNumber(item.getTrackingNumber());
                return orderedDetailRepository.save(orderedDetail);
            }else if(item.getStatus().equals("주문취소")){
                orderedDetail.setStatus(item.getStatus());
                return orderedDetailRepository.save(orderedDetail);
            }

        }
        else if(code.equals("ADMIN")){
            orderedDetail.setStatus(item.getStatus());
            return orderedDetailRepository.save(orderedDetail);

        }
    return null;
    }

    @Transactional
    public void hiddenOrders(int orderedId){
        Ordered ordered = orderedRepository.findByOrderId(orderedId);
        ordered.setIsHidden(1);
        orderedRepository.save(ordered);
    }

}
