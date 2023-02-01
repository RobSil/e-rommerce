package com.robsil.erommerce.service;

import com.robsil.erommerce.data.domain.Group;

public interface GroupService {

    Group findById(String id);

    void deleteById(String id);

}
