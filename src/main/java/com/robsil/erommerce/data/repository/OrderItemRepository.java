package com.robsil.erommerce.data.repository;

import com.robsil.erommerce.data.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
