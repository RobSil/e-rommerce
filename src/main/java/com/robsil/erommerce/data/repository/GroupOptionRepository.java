package com.robsil.erommerce.data.repository;

import com.robsil.erommerce.data.domain.GroupOption;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface GroupOptionRepository extends MongoRepository<GroupOption, String> {

//    https://stackoverflow.com/questions/73830901/how-to-get-list-of-all-object-ids-with-spring-data-mongodb-query-generation going to be List<GroupOption>
    @Query(value = "{'groupId': ?0}", fields = "{'id': 1}")
    List<String> getAllIdsByGroupId(String groupId);

}
