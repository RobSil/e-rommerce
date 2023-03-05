package com.robsil.erommerce;

import com.robsil.erommerce.data.domain.Category;
import com.robsil.erommerce.data.repository.CategoryRepository;
import com.robsil.erommerce.service.CategoryService;
import com.robsil.erommerce.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    CategoryRepository repository = mock(CategoryRepository.class);
    CategoryService underTest;

    @BeforeEach
    void beforeEach() {
        underTest = new CategoryServiceImpl(repository);
    }

    @Test
    public void testFindById() {
        Category category = new Category(null, "category1");

        Long id = 1L;

        category.setId(id);
        doReturn(Optional.of(category)).when(repository).findById(id);

        Category returned = underTest.findById(id);

        assertEquals(category, returned);
    }

}
