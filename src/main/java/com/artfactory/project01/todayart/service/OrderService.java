package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.entity.Order;
import com.artfactory.project01.todayart.entity.OrderDetail;
import com.artfactory.project01.todayart.model.OrderDetailForm;
import com.artfactory.project01.todayart.model.OrderForm;
import com.artfactory.project01.todayart.model.Period;
import com.artfactory.project01.todayart.repository.OrderDetailRepository;
import com.artfactory.project01.todayart.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Transactional
    public Order createOrder(OrderForm orderForm){
        Order order = orderRepository.save(orderForm.setOrder());

        List<OrderDetailForm> orderDetailList = orderForm.getOrderDetail();
        Integer orderId = order.getOrderId();

        for(OrderDetailForm item : orderDetailList){
            OrderDetail orderedItem = item.setOrderDetail(orderId);
            orderDetailRepository.save(orderedItem);
        }
        return order;
    }

    @Transactional
    public List<Order> getOrders(){
    return orderRepository.findAll();
    }

    @Transactional
    public List<Order> getOrdersByUser(int id){
    return orderRepository.findByMemberId(id);
    }

    @Transactional
    public List<Order> getOrdersByUserWithTerm(int id, Period period){
        Date startDate = period.getStartDate();
        Date endDate = period.getEndDate();
        return orderRepository.findByMemberIdWithTerm(id, startDate, endDate);
    }

    @Transactional
    public List<Order> getOrdersByUserWithTerm(Period period){
        Date startDate = period.getStartDate();
        Date endDate = period.getEndDate();
        return orderRepository.findByMemberIdWithTerm(startDate, endDate);
    }


    @Transactional
    public Order getOrder(int orderId, Member member){
        Order order = orderRepository.findById(orderId).get();
        int memberId = member.getMemberId();
        if(order.getMemberId()==memberId){
            return order;
        }
        return null;
    }

    @Transactional
    public Order getOrderForAdmin(int orderId){
        return orderRepository.findById(orderId).get();

    }
    // 구매자용 특정 주문정보디테일 보기
    @Transactional
    public List<OrderDetail> getOrderDetail(int orderId, Member member){
        Optional<Order> order = orderRepository.findById(orderId);
        int memberId = member.getMemberId();
        if(order.get().getMemberId()==memberId){
            List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrderId(orderId);
            return orderDetailList;
        }else{
            return null;
        }
    }
    // 관리자용 특정 주문 정보 디테일 보기
    @Transactional
    public List<OrderDetail> getOrderDetailForAdmin(int orderId){

        Optional<Order> order = orderRepository.findById(orderId);
            List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrderId(orderId);
            return orderDetailList;

    }

    @Transactional
    public Order patchOrder(Integer orderId, Integer shippingfee){
        Order order = orderRepository.findById(orderId).get();
        order.setShippingFee(shippingfee);
        return orderRepository.save(order);
    }



}
