package com.robsil.erommerce.service.facade;

import com.robsil.erommerce.data.domain.CartItem;
import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.model.cart.CartDto;
import com.robsil.erommerce.model.cart.DetailedCartDto;
import com.robsil.erommerce.model.cartItem.CartItemCreateRequest;
import com.robsil.erommerce.model.cartItem.MinimizedCartItemDto;
import com.robsil.erommerce.model.exception.ForbiddenException;
import com.robsil.erommerce.service.CartItemService;
import com.robsil.erommerce.service.CartService;
import com.robsil.erommerce.service.ProductService;
import com.robsil.erommerce.service.dtoMapper.ProductDtoMapper;
import com.robsil.erommerce.service.dtoMapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

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
                        .collect(Collectors.toList()))
                .build();
    }

    @Transactional
    public void clearAll(User user) {
        var cart = cartService.findByUserId(user);

        cartItemService.deleteAllByCartId(cart.getId());
    }

    @Transactional
    public CartItem addItem(User user, CartItemCreateRequest req) {
        var product = productService.findById(req.getProductId());
        var cart = cartService.findByUserId(user);

        return cartItemService.create(cart, product, req.getQuantity());
    }

    @Transactional
    public void deleteItem(User user, Long cartItemId) {
        var cart = cartService.findByUserId(user);

        var cartItem = cartItemService.findById(cartItemId);

        if (cartItem.getCart().getId().equals(cart.getId())) {
            throw new ForbiddenException("Can't delete foreign from cart.");
        }

        cartItemService.deleteById(cartItem.getId());
    }

}