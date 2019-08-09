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
    public ArrayList<OrderFormReturn> getOrders(){
        List<Ordered> orders = orderedRepository.findAll();
        return getOrderForm(orders);
    }

    @Transactional
    public ArrayList<OrderFormReturn> getOrdersByUser(int id){
        List<Ordered> orders = orderedRepository.findByMemberId(id);
        return getOrderForm(orders);
    }

    @Transactional ArrayList<OrderFormReturn> getOrderForm(List<Ordered> orders){
        ArrayList<OrderFormReturn> orderList= new ArrayList<>();
        for(Ordered order : orders){
            List<OrderedDetail> itemDetail = orderedDetailRepository.findAllByOrderId(order.getOrderId());
            for(OrderedDetail detail : itemDetail){
                OrderFormReturn item = new OrderFormReturn();
                item.setOrderDate(order.getOrderDate());
                item.setProductName(detail.getProductName());
                item.setProductPrice(detail.getProductPrice());
                item.setTrackingNumber(detail.getTrackingNumber());
                item.setStatus(detail.getStatus());
                orderList.add(item);
            }
        }
        return orderList;
    }


    @Transactional
    public ArrayList<OrderFormReturn> getOrdersWithTerm(Period period){
        Date startDate = period.getStartDate();
        Date endDate = period.getEndDate();
        List<Ordered> orderedList = orderedRepository.findByMemberIdWithTerm(startDate, endDate);
        return getOrderForm(orderedList);
    }


    @Transactional
    public ArrayList<OrderFormReturn> getOrdersByUserWithTerm(int id, Period period){
        Date startDate = period.getStartDate();
        Date endDate = period.getEndDate();
        List<Ordered> orderedList = orderedRepository.findByMemberIdWithTerm(id, startDate, endDate);
        return getOrderForm(orderedList);
    }



    @Transactional
    public List<Ordered> getOrdersByMemberId(int memberId){
        return orderedRepository.findByMemberId(memberId);
    }


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
    // 구매자용 특정 주문정보디테일 보기
    @Transactional
    public List<OrderedDetail> getOrderDetail(int orderId, Member member){
        Optional<Ordered> order = orderedRepository.findById(orderId);
        int memberId = member.getMemberId();
        if(order.get().getMemberId()==memberId){
            List<OrderedDetail> orderedDetailList = orderedDetailRepository.findAllByOrderId(orderId);
            return orderedDetailList;
        }else{
            return null;
        }
    }
    // 관리자용 특정 주문 정보 디테일 보기
    @Transactional
    public List<OrderedDetail> getOrderDetailForAdmin(int orderId){

        Optional<Ordered> order = orderedRepository.findById(orderId);
            List<OrderedDetail> orderedDetailList = orderedDetailRepository.findAllByOrderId(orderId);
            return orderedDetailList;

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
        ordered.setHidden(1);
        orderedRepository.save(ordered);
    }

}
