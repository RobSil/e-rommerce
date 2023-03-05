package com.robsil.erommerce.model.cart;

import com.robsil.erommerce.model.user.UserDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CartDto {

    private UserDto user;

}
