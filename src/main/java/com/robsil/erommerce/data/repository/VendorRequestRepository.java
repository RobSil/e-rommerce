package com.robsil.erommerce.data.repository;

import com.robsil.erommerce.data.domain.VendorRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VendorRequestRepository extends MongoRepository<VendorRequest, String> {
}
