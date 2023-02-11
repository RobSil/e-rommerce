package com.robsil.erommerce.service.impl;

import com.robsil.erommerce.data.domain.Group;
import com.robsil.erommerce.data.domain.Product;
import com.robsil.erommerce.data.repository.GroupRepository;
import com.robsil.erommerce.model.exception.EntityNotFoundException;
import com.robsil.erommerce.service.GroupOptionService;
import com.robsil.erommerce.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupOptionService groupOptionService;

    @Override
    public Group findById(String id) {
        return groupRepository.findById(id).orElseThrow(() -> {
            log.info("findById: can't find group by ID: %s".formatted(id));
            return new EntityNotFoundException("Group not found.");
        });
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        if (id == null || id.isEmpty()) {
            return;
        }

        var groupIds = groupOptionService.getAllIdsByGroup(id);
        groupOptionService.deleteAllByIds(groupIds);

        groupRepository.deleteById(id);
    }
}
