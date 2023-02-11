package com.robsil.erommerce.service.impl;

import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.data.domain.Vendor;
import com.robsil.erommerce.data.repository.VendorRepository;
import com.robsil.erommerce.model.exception.ForbiddenException;
import com.robsil.erommerce.model.user.UserVendorRequest;
import com.robsil.erommerce.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public Vendor saveEntity(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor create(UserVendorRequest req, User requestUser, User adminUser) {
        if (!adminUser.isAdmin()) {
            throw new ForbiddenException("Current user is not an admin");
        }

        var vendor = Vendor.builder()
                .approvedAdminUserId(adminUser.getId())
                .name(req.getName())
                .description(req.getAboutCompany())
                .averageAmountOfEmployers(req.getAverageAmountOfEmployers())
                .purpose(req.getPurpose())
                .generalTypeOfProducts(req.getGeneralTypeOfProducts())
                .contactEmail(req.getContactEmail())
                .contactPhoneNumber(req.getContactPhoneNumber())
                .build();

//        vendor.getOwners().add(requestUser.getId());

        vendor = saveEntity(vendor);

        return vendor;
    }
}
