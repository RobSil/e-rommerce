package com.robsil.erommerce.data.domain;

import com.robsil.erommerce.model.PaymentStatus;
import com.robsil.erommerce.model.PaymentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentType type;

    private BigDecimal amount;

}
