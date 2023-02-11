package com.robsil.erommerce.service;

import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.data.domain.VendorRequest;
import com.robsil.erommerce.model.user.UserVendorRequest;
import com.robsil.erommerce.model.vendor.VendorApproveResponse;

public interface VendorRequestService {
    VendorRequest saveEntity(VendorRequest vr);
    VendorRequest findById(String vendorRequestId);
    VendorRequest create(UserVendorRequest req, User user);

}
