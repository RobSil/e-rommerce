package com.robsil.erommerce.data.repository;

import com.robsil.erommerce.data.domain.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group, String> {
}
