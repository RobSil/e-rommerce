package com.robsil.erommerce.service.dtomapper;

import com.robsil.erommerce.data.domain.CartItem;
import com.robsil.erommerce.model.cartitem.CartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CartItemDtoMapper implements Function<CartItem, CartItemDto> {

    private final ProductDtoMapper productDtoMapper;
    private final CartDtoMapper cartDtoMapper;

    @Override
    public CartItemDto apply(CartItem cartItem) {
        return CartItemDto.builder()
                .cart(cartDtoMapper.apply(cartItem.getCart()))
                .product(productDtoMapper.apply(cartItem.getProduct()))
                .quantity(cartItem.getQuantity())
                .price(cartItem.getPrice())
                .build();
    }
}
