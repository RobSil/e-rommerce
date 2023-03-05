package com.robsil.erommerce.controller;

import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.model.cart.DetailedCartDto;
import com.robsil.erommerce.service.facade.CartFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartFacadeService cartFacadeService;

    @GetMapping
    public DetailedCartDto getByCurrentUser(@AuthenticationPrincipal User user) {
        return cartFacadeService.findDetailedCartByUser(user);
    }

}
