package com.robsil.erommerce.data.repository;

import com.robsil.erommerce.data.domain.GroupOption;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupOptionRepository extends MongoRepository<GroupOption, String> {
}
