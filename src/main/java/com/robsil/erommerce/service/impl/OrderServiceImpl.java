package com.robsil.erommerce.service.impl;

import com.robsil.erommerce.data.domain.Order;
import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.data.repository.OrderRepository;
import com.robsil.erommerce.model.OrderStatus;
import com.robsil.erommerce.model.exception.EntityNotFoundException;
import com.robsil.erommerce.model.order.OrderDetails;
import com.robsil.erommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private Order saveEntity(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }

    @Override
    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUserId(user.getId());
    }

    @Override
    public Page<Order> findAllByUser(User user, Pageable pageable) {
        return orderRepository.findAllByUserId(user.getId(), pageable);
    }

    @Override
    @Transactional
    public Order create(User user, OrderStatus status, OrderDetails details) {
        var order = Order.builder()
                .user(user)
                .status(status)
                .details(details)
                .build();

        order = saveEntity(order);

        return order;
    }

    @Override
    @Transactional
    public Order changeStatus(Order order, OrderStatus newStatus) {
        order.setStatus(newStatus);
        order = saveEntity(order);

        return order;
    }

    @Override
    @Transactional
    public Order changeStatus(Long orderId, OrderStatus newStatus) {
        return changeStatus(findById(orderId), newStatus);
    }
}
