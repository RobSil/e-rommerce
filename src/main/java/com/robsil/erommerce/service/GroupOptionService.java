package com.robsil.erommerce.service;

import com.robsil.erommerce.data.domain.GroupOption;

import java.util.List;

public interface GroupOptionService {

    GroupOption findById(String id);

    GroupOption saveEntity(GroupOption groupOption);

    List<String> getAllIdsByGroup(String groupId);

    void deleteById(String id);
    void deleteAllByIds(Iterable<String> ids);

}
