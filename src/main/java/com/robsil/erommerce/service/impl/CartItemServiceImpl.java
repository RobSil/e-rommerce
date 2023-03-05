package com.robsil.erommerce.service.impl;

import com.robsil.erommerce.data.domain.Cart;
import com.robsil.erommerce.data.domain.CartItem;
import com.robsil.erommerce.data.domain.Product;
import com.robsil.erommerce.data.repository.CartItemRepository;
import com.robsil.erommerce.model.exception.EntityNotFoundException;
import com.robsil.erommerce.service.CartItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    private CartItem saveEntity(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem findById(Long cartItemId) {
        return cartItemRepository
                .findById(cartItemId).orElseThrow(() -> new EntityNotFoundException("CartItem not found."));
    }

    @Override
    public List<CartItem> findAllByCartId(Long cartId) {
        return cartItemRepository.findAllByCartId(cartId);
    }

    @Override
    public Page<CartItem> findAllByCartId(Long cartId, Pageable pageable) {
        return cartItemRepository.findAllByCartId(cartId, pageable);
    }

    @Transactional
    @Override
    public CartItem create(Cart cart, Product product, BigDecimal quantity) {
        var cartItem = CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(quantity)
                .price(quantity.multiply(product.getPrice()))
                .build();

        cartItem = saveEntity(cartItem);

        return cartItem;
    }

    @Transactional
    @Override
    public void changeQuantity(Long cartItemId, BigDecimal quantity) {
        cartItemRepository.changeQuantity(cartItemId, quantity);
//        CartItem cartItem = findById(cartItemId);
//
//        cartItem.setQuantity(quantity);
//        cartItem.setPrice(quantity.multiply(cartItem.getProduct().getPrice()));
//
//        cartItem = saveEntity(cartItem);
//
//        return cartItem;
    }

    @Override
    public void deleteById(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public void deleteAllByCartId(Long cartId) {
        cartItemRepository.deleteAllByCartId(cartId);
    }
}
