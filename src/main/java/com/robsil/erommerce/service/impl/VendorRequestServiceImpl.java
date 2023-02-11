package com.robsil.erommerce.service.impl;

import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.data.domain.VendorRequest;
import com.robsil.erommerce.data.repository.VendorRequestRepository;
import com.robsil.erommerce.model.exception.EntityNotFoundException;
import com.robsil.erommerce.model.user.UserVendorRequest;
import com.robsil.erommerce.model.vendor.VendorApproveResponse;
import com.robsil.erommerce.service.VendorRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class VendorRequestServiceImpl implements VendorRequestService {

    private final VendorRequestRepository vendorRequestRepository;

    public VendorRequest saveEntity(VendorRequest vr) {
        return vendorRequestRepository.save(vr);
    }

    @Override
    public VendorRequest findById(String vendorRequestId) {
        return vendorRequestRepository.findById(vendorRequestId).orElseThrow(() -> {
            log.info("findById: can't find vendorRequest by ID: %s".formatted(vendorRequestId));
            return new EntityNotFoundException("Vendor Request not found.");
        });
    }

    @Override
    public VendorRequest create(UserVendorRequest req, User user) {
        var vr = VendorRequest.builder()
                .userId(user.getId())
                .request(req)
                .build();

        vr = saveEntity(vr);

        return vr;
    }
}
