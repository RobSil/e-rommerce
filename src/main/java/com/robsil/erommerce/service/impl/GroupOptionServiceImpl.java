package com.robsil.erommerce.service.impl;

import com.robsil.erommerce.data.domain.GroupOption;
import com.robsil.erommerce.data.repository.GroupOptionRepository;
import com.robsil.erommerce.service.GroupOptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class GroupOptionServiceImpl implements GroupOptionService {

    private final GroupOptionRepository groupOptionRepository;

    @Override
    public GroupOption findById(String id) {
        return null;
    }

    @Override
    public GroupOption saveEntity(GroupOption groupOption) {
        return null;
    }

    @Override
    public List<String> getAllIdsByGroup(String groupId) {
        return null;
    }

    @Override
    public void deleteById(String id) {
        groupOptionRepository.deleteById(id);
    }

    @Override
    public void deleteAllByIds(Iterable<String> ids) {
        groupOptionRepository.deleteAllById(ids);
    }
}
