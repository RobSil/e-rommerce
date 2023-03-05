package com.robsil.erommerce.data.repository;

import com.robsil.erommerce.data.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select order from Order order where order.user.id = :userId")
    List<Order> findAllByUserId(Long userId);

    @Query("select order from Order order where order.user.id = :userId")
    Page<Order> findAllByUserId(Long userId, Pageable pageable);

}
