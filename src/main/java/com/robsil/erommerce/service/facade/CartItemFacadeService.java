package com.robsil.erommerce.service.facade;

import com.robsil.erommerce.data.domain.CartItem;
import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.model.cartItem.CartItemChangeQuantityRequest;
import com.robsil.erommerce.model.cartItem.CartItemCreateRequest;
import com.robsil.erommerce.model.cartItem.CartItemQuantityChangeResponse;
import com.robsil.erommerce.model.exception.ForbiddenException;
import com.robsil.erommerce.service.CartItemService;
import com.robsil.erommerce.service.CartService;
import com.robsil.erommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartItemFacadeService {

    private final CartItemService cartItemService;
    private final ProductService productService;
    private final CartService cartService;

    @Transactional
    public CartItem addItem(User user, CartItemCreateRequest req) {
        var product = productService.findById(req.getProductId());
        var cart = cartService.findByUserId(user);

        return cartItemService.create(cart, product, req.getQuantity());
    }

    public CartItemQuantityChangeResponse changeQuantity(CartItemChangeQuantityRequest req, Long cartItemId, User user) {
        var cartItem = cartItemService.findById(cartItemId);

        if (!cartItem.getCart().getUser().getId().equals(user.getId())) {
            throw new ForbiddenException("Can't change quantity of item from foreign cart.");
        }

        var oldQuantity = cartItem.getQuantity();

        cartItemService.changeQuantity(cartItem.getId(), req.getNewQuantity());

        return CartItemQuantityChangeResponse.builder()
                .message("Success")
                .difference(cartItem.getQuantity().subtract(oldQuantity))
                .newQuantity(req.getNewQuantity())
                .build();
    }

    @Transactional
    public void delete(Long cartItemId, User user) {
        var cartItem = cartItemService.findById(cartItemId);

        if (cartItem.getCart().getUser().getId().equals(user.getId())) {
            throw new ForbiddenException("Can't delete foreign from cart.");
        }

        cartItemService.deleteById(cartItem.getId());
    }

}
