package com.robsil.erommerce.data.repository;

import com.robsil.erommerce.data.domain.Vendor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VendorRepository extends MongoRepository<Vendor, String> {
}
