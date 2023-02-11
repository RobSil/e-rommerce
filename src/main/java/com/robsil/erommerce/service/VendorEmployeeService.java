package com.robsil.erommerce.service;

import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.data.domain.Vendor;
import com.robsil.erommerce.data.domain.VendorEmployee;
import com.robsil.erommerce.model.VendorEmployeePosition;

public interface VendorEmployeeService {

    VendorEmployee create(User user, Vendor vendor, VendorEmployeePosition position);

}
