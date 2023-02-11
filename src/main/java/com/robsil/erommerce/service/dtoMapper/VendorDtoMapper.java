package com.robsil.erommerce.service.dtoMapper;

import com.robsil.erommerce.data.domain.Vendor;
import com.robsil.erommerce.model.vendor.VendorDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class VendorDtoMapper implements Function<Vendor, VendorDto> {
    @Override
    public VendorDto apply(Vendor vendor) {
        return null;
    }
}
