package com.robsil.erommerce.data.repository;

import com.robsil.erommerce.data.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
