package com.robsil.erommerce.service.dtomapper;

import com.robsil.erommerce.data.domain.Cart;
import com.robsil.erommerce.model.cart.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CartDtoMapper implements Function<Cart, CartDto> {

    private final UserDtoMapper userDtoMapper;

    @Override
    public CartDto apply(Cart cart) {
        return CartDto.builder()
                .user(userDtoMapper.apply(cart.getUser()))
                .build();
    }
}
