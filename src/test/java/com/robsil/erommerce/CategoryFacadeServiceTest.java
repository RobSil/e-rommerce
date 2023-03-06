package com.robsil.erommerce;

import com.robsil.erommerce.data.domain.Category;
import com.robsil.erommerce.data.domain.Product;
import com.robsil.erommerce.data.repository.CategoryRepository;
import com.robsil.erommerce.data.repository.ProductRepository;
import com.robsil.erommerce.service.CategoryService;
import com.robsil.erommerce.service.ProductService;
import com.robsil.erommerce.service.facade.CategoryFacadeService;
import com.robsil.erommerce.service.impl.CategoryServiceImpl;
import com.robsil.erommerce.service.impl.ProductServiceImpl;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(initializers = {CategoryFacadeServiceTest.Initializer.class})
@Testcontainers
public class CategoryFacadeServiceTest {

    @Container
    public static final JdbcDatabaseContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:12.12")
            .withDatabaseName("erommerce_test_db")
            .withUsername("postgres")
            .withPassword("root")
            .withInitScript("dbschema.sql");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "spring.flyway.enabled=false"
            ).applyTo(applicationContext.getEnvironment());
        }
    }

    @Autowired
    public CategoryRepository categoryRepository;
    @Autowired
    public ProductRepository productRepository;

    @Spy
    public CategoryService injectedCategoryService = new CategoryServiceImpl(categoryRepository);
    @Spy
    public ProductService injectedProductService = new ProductServiceImpl(productRepository);
    public CategoryFacadeService injectedUnderTest = new CategoryFacadeService(injectedCategoryService, injectedProductService);

    public CategoryService categoryService = mock(CategoryService.class);
    public ProductService productService = mock(ProductService.class);
    public CategoryFacadeService underTest = new CategoryFacadeService(categoryService, productService);

    @BeforeEach
    void init() {
        underTest = new CategoryFacadeService(categoryService, productService);
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void deleteById() {
        //given
        var c1 = categoryRepository.save(new Category(null, "c1"));
        var c2 = categoryRepository.save(new Category(null, "c2"));
        var c3 = categoryRepository.save(new Category(c1, "c3"));
        var c4 = categoryRepository.save(new Category(c1, "c4"));
        var c5 = categoryRepository.save(new Category(c3, "c5"));
        var c6 = categoryRepository.save(new Category(c3, "c6"));

        var p1 = productRepository.save(new Product(c6, "p1", null, null, null, null, null, false, null));
        var p2 = productRepository.save(new Product(c6, "p2", null, null, null, null, null, false, null));
        var p3 = productRepository.save(new Product(c5, "p3", null, null, null, null, null, false, null));
        var p4 = productRepository.save(new Product(c1, "p4", null, null, null, null, null, false, null));
        var p5 = productRepository.save(new Product(c1, "p5", null, null, null, null, null, false, null));
        var p6 = productRepository.save(new Product(c1, "p6", null, null, null, null, null, false, null));
        var p7 = productRepository.save(new Product(c2, "p7", null, null, null, null, null, false, null));

        //when

        when(categoryService.findById(c1.getId())).thenReturn(c1);
        when(categoryService.findAllByParentId(c1.getId())).thenReturn(List.of(c3, c4));
        when(categoryService.findAllByParentId(c3.getId())).thenReturn(List.of(c5, c6));
        when(categoryService.findAllByParentId(c4.getId())).thenReturn(List.of());
        when(categoryService.findAllByParentId(c5.getId())).thenReturn(List.of());
        when(categoryService.findAllByParentId(c6.getId())).thenReturn(List.of());
        underTest.deleteById(c1.getId());

        //then
        verify(productService, times(1)).deleteAllByCategoryId(c1.getId());
        verify(productService, times(1)).deleteAllByCategoryId(c3.getId());
        verify(productService, times(1)).deleteAllByCategoryId(c4.getId());
        verify(productService, times(1)).deleteAllByCategoryId(c5.getId());
        verify(productService, times(1)).deleteAllByCategoryId(c6.getId());

        verify(categoryService, times(1)).deleteById(c1.getId());
        verify(categoryService, times(1)).deleteById(c3.getId());
        verify(categoryService, times(1)).deleteById(c4.getId());
        verify(categoryService, times(1)).deleteById(c5.getId());
        verify(categoryService, times(1)).deleteById(c6.getId());
    }

    @Test
    @Transactional
    void deleteByIdWithInjected() {
        //given
        injectedCategoryService = spy(new CategoryServiceImpl(categoryRepository));
        injectedProductService = spy(new ProductServiceImpl(productRepository));
        injectedUnderTest = new CategoryFacadeService(injectedCategoryService, injectedProductService);

        var c1 = categoryRepository.save(new Category(null, "c1"));
        var c2 = categoryRepository.save(new Category(null, "c2"));
        var c3 = categoryRepository.save(new Category(c1, "c3"));
        var c4 = categoryRepository.save(new Category(c1, "c4"));
        var c5 = categoryRepository.save(new Category(c3, "c5"));
        var c6 = categoryRepository.save(new Category(c3, "c6"));

        var p1 = productRepository.save(new Product(c6, "p1", null, null, null, null, null, false, null));
        var p2 = productRepository.save(new Product(c6, "p2", null, null, null, null, null, false, null));
        var p3 = productRepository.save(new Product(c5, "p3", null, null, null, null, null, false, null));
        var p4 = productRepository.save(new Product(c1, "p4", null, null, null, null, null, false, null));
        var p5 = productRepository.save(new Product(c1, "p5", null, null, null, null, null, false, null));
        var p6 = productRepository.save(new Product(c1, "p6", null, null, null, null, null, false, null));
        var p7 = productRepository.save(new Product(c2, "p7", null, null, null, null, null, false, null));

        //when

//        when(injectedCategoryService.findById(1L)).thenReturn(c1);
//        when(injectedCategoryService.findAllByParentId(c1.getId())).thenReturn(List.of(c3, c4));
//        when(injectedCategoryService.findAllByParentId(c3.getId())).thenReturn(List.of(c5, c6));
//        when(injectedCategoryService.findAllByParentId(c4.getId())).thenReturn(List.of());
//        when(injectedCategoryService.findAllByParentId(c5.getId())).thenReturn(List.of());
//        when(injectedCategoryService.findAllByParentId(c6.getId())).thenReturn(List.of());
        injectedUnderTest.deleteById(c1.getId());

        //then
        verify(injectedProductService, times(1)).deleteAllByCategoryId(1L);
        verify(injectedProductService, times(1)).deleteAllByCategoryId(3L);
        verify(injectedProductService, times(1)).deleteAllByCategoryId(4L);
        verify(injectedProductService, times(1)).deleteAllByCategoryId(5L);
        verify(injectedProductService, times(1)).deleteAllByCategoryId(6L);

        verify(injectedCategoryService, times(1)).deleteById(1L);
        verify(injectedCategoryService, times(1)).deleteById(3L);
        verify(injectedCategoryService, times(1)).deleteById(4L);
        verify(injectedCategoryService, times(1)).deleteById(5L);
        verify(injectedCategoryService, times(1)).deleteById(6L);

        assertEquals(1L, categoryRepository.count());
        assertEquals(1L, productRepository.count());
    }
}
