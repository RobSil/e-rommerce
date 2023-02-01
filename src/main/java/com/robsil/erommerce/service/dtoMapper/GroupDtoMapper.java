package com.robsil.erommerce.service.dtoMapper;

import com.robsil.erommerce.data.domain.Group;
import com.robsil.erommerce.model.group.GroupDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GroupDtoMapper implements Function<Group, GroupDto> {
    @Override
    public GroupDto apply(Group group) {

        return GroupDto.builder()
                .id(group.getId())
                .title(group.getTitle())
                .name(group.getName())
                .build();
    }
}
