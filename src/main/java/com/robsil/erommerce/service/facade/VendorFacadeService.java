package com.robsil.erommerce.service.facade;

import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.model.exception.HttpConflictException;
import com.robsil.erommerce.model.vendor.VendorApproveResponse;
import com.robsil.erommerce.model.vendor.VendorDto;
import com.robsil.erommerce.service.*;
import com.robsil.erommerce.service.dtoMapper.VendorDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class VendorFacadeService {

    private final UserService userService;
    private final VendorService vendorService;
    private final VendorRequestService vendorRequestService;
    private final VendorEmployeeService vendorEmployeeService;
    private final VendorEmployeePermissionService vendorEmployeePermissionService;
    private final VendorDtoMapper vendorDtoMapper;

    public VendorDto approve(String vendorRequestId, User adminUser) {
        var vr = vendorRequestService.findById(vendorRequestId);
        var vrUser = userService.findById(vr.getUserId());

        if (vr.isApproved()) {
            throw new HttpConflictException("Vendor Request is already approved.");
        }

        var vendor = vendorService.create(vr.getRequest(), vrUser, adminUser);

        vr.setApproved(true);
        vr.setApprovedBy(adminUser.getId());
        vr = vendorRequestService.saveEntity(vr);

        vendorEmployeePermissionService.init(vendor);

        return vendorDtoMapper.apply(vendor);
    }

}
