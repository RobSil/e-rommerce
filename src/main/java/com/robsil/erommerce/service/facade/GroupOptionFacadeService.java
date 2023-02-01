package com.robsil.erommerce.service.facade;

import com.robsil.erommerce.data.domain.GroupOption;
import com.robsil.erommerce.model.groupOption.GroupOptionCreateRequest;
import com.robsil.erommerce.model.groupOption.GroupOptionSaveRequest;
import com.robsil.erommerce.service.GroupOptionService;
import com.robsil.erommerce.service.GroupService;
import com.robsil.erommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class GroupOptionFacadeService {

    private final GroupOptionService groupOptionService;
    private final ProductService productService;
    private final GroupService groupService;

    public GroupOption create(GroupOptionCreateRequest req) {

        var product = productService.findBySku(req.getSku());
        var group = groupService.findById(req.getGroupId());

        var go = GroupOption.builder()
                .groupId(group.getId())
                .productId(product.getId())
                .value(req.getValue())
                .isActive(req.isActive())
                .status(product.getStatus())
                .build();

        go = groupOptionService.saveEntity(go);

        return go;
    }

    public GroupOption save(GroupOptionSaveRequest req) {

        var go = groupOptionService.findById(req.getGroupId());
        var product = productService.findBySku(req.getSku());
        var group = groupService.findById(req.getGroupId());


        go.setGroupId(group.getId());
        go.setProductId(product.getId());
        go.setValue(req.getValue());
        go.setActive(req.isActive());
        go.setStatus(product.getStatus());

        go = groupOptionService.saveEntity(go);

        return go;
    }

}
