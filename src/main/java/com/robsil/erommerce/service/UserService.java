package com.robsil.erommerce.service;

import com.robsil.erommerce.data.domain.User;

public interface UserService {

    User getByEmail(String email);

}
