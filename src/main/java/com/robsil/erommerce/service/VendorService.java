package com.robsil.erommerce.service;

import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.data.domain.Vendor;
import com.robsil.erommerce.model.user.UserVendorRequest;

public interface VendorService {

    Vendor create(UserVendorRequest req, User requestUser, User adminUser);

}
