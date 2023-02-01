package com.robsil.erommerce.service.dtoMapper;

import com.robsil.erommerce.data.domain.GroupOption;
import com.robsil.erommerce.model.ProductStatus;
import com.robsil.erommerce.model.groupOption.GroupOptionDto;
import com.robsil.erommerce.model.product.ProductDto;
import com.robsil.erommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class GroupOptionDtoMapper implements Function<GroupOption, GroupOptionDto> {

    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;

    @Override
    public GroupOptionDto apply(GroupOption groupOption) {
        var product = productService.findById(groupOption.getProductId());

        return GroupOptionDto.builder()
                .id(groupOption.getId())
                .product(productDtoMapper.apply(product))
                .groupId(groupOption.getGroupId())
                .value(groupOption.getValue())
                .isActive(groupOption.isActive())
                .status(groupOption.getStatus())
                .build();
    }
}
