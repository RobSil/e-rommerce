package com.robsil.erommerce.service.facade;

import com.robsil.erommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CategoryFacadeService {

    private final CategoryService categoryService;

}
