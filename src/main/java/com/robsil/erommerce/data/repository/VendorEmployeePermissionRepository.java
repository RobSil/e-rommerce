package com.robsil.erommerce.data.repository;

import com.robsil.erommerce.data.domain.VendorEmployeePermission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VendorEmployeePermissionRepository extends MongoRepository<VendorEmployeePermission, String> {
}
