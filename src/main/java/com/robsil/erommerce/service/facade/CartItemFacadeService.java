package com.robsil.erommerce.service.facade;

import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.model.cartItem.CartItemChangeQuantityRequest;
import com.robsil.erommerce.model.cartItem.CartItemQuantityChangeResponse;
import com.robsil.erommerce.model.exception.ForbiddenException;
import com.robsil.erommerce.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemFacadeService {

    private final CartItemService cartItemService;

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

}
