package com.robsil.erommerce.service;

import com.robsil.erommerce.data.domain.Order;
import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.model.OrderStatus;
import com.robsil.erommerce.model.order.OrderDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    Order findById(Long orderId);
    List<Order> findAllByUser(User user);
    Page<Order> findAllByUser(User user, Pageable pageable);
    Order create(User user, OrderStatus status, OrderDetails details);
    Order changeStatus(Order order, OrderStatus newStatus);
    Order changeStatus(Long orderId, OrderStatus newStatus);

}
