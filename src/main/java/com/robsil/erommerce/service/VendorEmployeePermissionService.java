package com.robsil.erommerce.service;

import com.robsil.erommerce.data.domain.Vendor;
import com.robsil.erommerce.data.domain.VendorEmployeePermission;

public interface VendorEmployeePermissionService {

    VendorEmployeePermission findByVendorId(String vendorId);
    VendorEmployeePermission init(Vendor vendor);

}
