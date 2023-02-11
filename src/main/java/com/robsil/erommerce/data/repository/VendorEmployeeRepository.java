package com.robsil.erommerce.data.repository;

import com.robsil.erommerce.data.domain.VendorEmployee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VendorEmployeeRepository extends MongoRepository<VendorEmployee, String> {
}
