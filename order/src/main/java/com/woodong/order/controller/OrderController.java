package com.woodong.order.controller;

import com.woodong.order.data.dto.OrderDto;
import com.woodong.order.data.request.RequestOrder;
import com.woodong.order.data.response.ResponseOrder;
import com.woodong.order.entity.OrderEntity;
import com.woodong.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId){
        List<OrderEntity> orderList = orderService.getOrdersByUserId(userId);
        List<ResponseOrder> result = new ArrayList<>();
        orderList.forEach(order -> result.add(new ModelMapper().map(order, ResponseOrder.class)));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId")  String userId, @RequestBody RequestOrder orderDetails){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        OrderDto orderDto = modelMapper.map(orderDetails, OrderDto.class);
        orderDto.setUserId(userId);
        OrderDto createDto = orderService.createOrder(orderDto);
        ResponseOrder returnValue = modelMapper.map(createDto, ResponseOrder.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
}
