package com.woodong.order.controller;

import com.woodong.order.data.dto.OrderDto;
import com.woodong.order.data.request.RequestOrder;
import com.woodong.order.data.response.ResponseOrder;
import com.woodong.order.entity.OrderEntity;
import com.woodong.order.queue.OrderProducer;
import com.woodong.order.queue.KafkaProducer;
import com.woodong.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final KafkaProducer kafkaProducer;
    private final OrderProducer orderProducer;

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId) {
        log.info("Before retrieve orders data");
        List<OrderEntity> orderList = orderService.getOrdersByUserId(userId);
        List<ResponseOrder> result = new ArrayList<>();
        orderList.forEach(order -> result.add(new ModelMapper().map(order, ResponseOrder.class)));
        log.info("Add retrieved orders data");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId, @RequestBody RequestOrder orderDetails) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        OrderDto orderDto = modelMapper.map(orderDetails, OrderDto.class);
        orderDto.setUserId(userId);

//        OrderDto createDto = orderService.createOrder(orderDto);
//        ResponseOrder returnValue = modelMapper.map(createDto, ResponseOrder.class);

        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDetails.getQty() * orderDetails.getUnitPrice());
        ResponseOrder returnValue = modelMapper.map(orderDto, ResponseOrder.class);

        kafkaProducer.send("example-order-topic", orderDto);
        orderProducer.send("orders", orderDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
}
