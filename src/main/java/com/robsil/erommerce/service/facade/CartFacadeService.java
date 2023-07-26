package com.robsil.erommerce.service.facade;

import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.model.cart.CartDto;
import com.robsil.erommerce.model.cart.DetailedCartDto;
import com.robsil.erommerce.model.cartitem.MinimizedCartItemDto;
import com.robsil.erommerce.service.CartItemService;
import com.robsil.erommerce.service.CartService;
import com.robsil.erommerce.service.ProductService;
import com.robsil.erommerce.service.dtomapper.ProductDtoMapper;
import com.robsil.erommerce.service.dtomapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CartFacadeService {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final ProductService productService;

    private final UserDtoMapper userDtoMapper;
    private final ProductDtoMapper productDtoMapper;

    public DetailedCartDto findDetailedCartByUser(User user) {
        var cart = cartService.findByUserId(user);
        var cartItems = cartItemService.findAllByCartId(cart.getId());

        return DetailedCartDto.builder()
                .cartDto(new CartDto(userDtoMapper.apply(cart.getUser())))
                .items(cartItems.stream()
                        .map(cartItem -> new MinimizedCartItemDto(productDtoMapper.apply(
                                cartItem.getProduct()),
                                cartItem.getQuantity(),
                                cartItem.getPrice()))
                        .toList())
                .build();
    }

    @Transactional
    public void deleteAll(User user) {
        var cart = cartService.findByUserId(user);

        cartItemService.deleteAllByCartId(cart.getId());
    }

}
