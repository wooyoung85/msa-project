package com.woodong.order.repository;

import com.woodong.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findByOrderId(String orderId);

    List<OrderEntity> findByUserId(String userId);
}
