package com.woodong.order.service;

import com.woodong.order.data.dto.OrderDto;
import com.woodong.order.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);

    OrderDto getOrderByOrderId(String orderId);

    List<OrderEntity> getOrdersByUserId(String userId);
}
