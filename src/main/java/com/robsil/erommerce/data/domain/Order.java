package com.robsil.erommerce.data.domain;


import com.robsil.erommerce.model.OrderStatus;
import com.robsil.erommerce.model.order.OrderDetails;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private OrderDetails details;

}
