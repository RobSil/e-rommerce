package com.robsil.erommerce.service;

import com.robsil.erommerce.data.domain.Cart;
import com.robsil.erommerce.data.domain.User;

public interface CartService {

    Cart findById(Long id);
    // find by user id. Whenever result is null, should create cart for a user.
    Cart findByUserId(User user);

}
