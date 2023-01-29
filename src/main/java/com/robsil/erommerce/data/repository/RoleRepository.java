package com.robsil.erommerce.data.repository;

import com.robsil.erommerce.data.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
}
