package com.robsil.erommerce;

import com.robsil.erommerce.data.domain.Category;
import com.robsil.erommerce.data.repository.CategoryRepository;
import com.robsil.erommerce.model.category.CategoryCreateRequest;
import com.robsil.erommerce.model.category.CategorySaveRequest;
import com.robsil.erommerce.model.exception.EntityNotFoundException;
import com.robsil.erommerce.service.CategoryService;
import com.robsil.erommerce.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

//    @Captor
    ArgumentCaptor<Category> captor = ArgumentCaptor.forClass(Category.class);

    CategoryRepository repository = mock(CategoryRepository.class);
    CategoryService underTest;

    @BeforeEach
    void beforeEach() {
        underTest = new CategoryServiceImpl(repository);
    }

    @Test
    @DisplayName("Find by id")
    public void findById() {
        //given
        Long id = 1L;
        Category category = new Category(null, "category1");
        category.setId(id);

        doReturn(Optional.of(category)).when(repository).findById(1L);

        //when
        Category returned = underTest.findById(id);

        //then
        assertEquals(category, returned);
    }

    @Test
    @DisplayName("Find by id not found")
    public void findByIdNotFound() {
        //given
        Long id = 1L;

        doReturn(Optional.empty()).when(repository).findById(id);

        //when

        //then

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class, () -> underTest.findById(id));

        assertEquals(e.getMessage(), "Category not found");


    }

    @Test
    @DisplayName("find all roots")
    public void findAllRoots() {
        //given
        Category category1 = Category.builder()
                .id(1L)
                .parent(null)
                .title("title1")
                .build();

        Category category2 = Category.builder()
                .id(2L)
                .parent(null)
                .title("title2")
                .build();

        Category category3 = Category.builder()
                .id(3L)
                .parent(null)
                .title("title3")
                .build();

        Category category4 = Category.builder()
                .id(4L)
                .parent(null)
                .title("title4")
                .build();

        Category category5 = Category.builder()
                .id(5L)
                .parent(category4)
                .title("title5")
                .build();

        Category category6 = Category.builder()
                .id(6L)
                .parent(category5)
                .title("title6")
                .build();

        //when

        List<Category> roots = List.of(category1, category2, category3, category4);
        when(repository.findAllByParentId(null)).thenReturn(roots);
        List<Category> returnedRoots = underTest.findAllRoots();

        //then

        assertEquals(roots.size(), returnedRoots.size());
        assertTrue(roots.containsAll(returnedRoots));
    }

    @Test
    @DisplayName("create root")
    void createRoot() {
        //given
        CategoryCreateRequest req = new CategoryCreateRequest(null, "category1");

        Category expected = Category.builder()
                .id(1L)
                .title("category1")
                .parent(null)
                .build();
        //when

        when(repository.save(captor.capture())).thenReturn(expected);
        Category returned = underTest.create(req);

        //then
        verify(repository, times(1)).save(any());
        Category captured = captor.getValue();
        assertNotNull(captured);

        assertEquals(returned, expected);

    }

    @Test
    @DisplayName("create non root")
    void createNonRoot() {
        //given
        Category root = Category.builder()
                .id(1L)
                .title("category1")
                .parent(null)
                .build();

        Category expected = Category.builder()
                .id(1L)
                .title("category2")
                .parent(root)
                .build();

        CategoryCreateRequest req = new CategoryCreateRequest(root.getId(), "category1");
        //when

        when(repository.findById(root.getId())).thenReturn(Optional.of(root));
        when(repository.save(captor.capture())).thenReturn(expected);
        Category returned = underTest.create(req);

        //then
        verify(repository, times(1)).save(any());
        Category captured = captor.getValue();
        assertNotNull(captured);

        assertEquals(returned, expected);
        assertEquals(returned.getParent(), root);

    }

    @Test
    @DisplayName("save root")
    void saveRoot() {
        //given
        Category find = Category.builder()
                .id(1L)
                .title("category1")
                .parent(null)
                .build();

        Category expected = Category.builder()
                .id(1L)
                .title("categoryNewTitle1")
                .parent(null)
                .build();

        CategorySaveRequest req = new CategorySaveRequest(1L, null, "categoryNewTitle1");
        //when

        when(repository.findById(1L)).thenReturn(Optional.of(find));
        when(repository.save(captor.capture())).thenReturn(expected);
        Category returned = underTest.save(req);

        //then
        verify(repository, times(1)).save(any());
        Category captured = captor.getValue();
        assertNotNull(captured);

        assertEquals(returned, expected);
        assertEquals(expected, captured);
    }

    @Test
    @DisplayName("save non root")
    void saveNonRoot() {
        //given
        Category parentFind = Category.builder()
                .id(1L)
                .title("category1")
                .parent(null)
                .build();

        Category find = Category.builder()
                .id(2L)
                .title("category2")
                .parent(parentFind)
                .build();

        Category expected = Category.builder()
                .id(2L)
                .title("categoryNewTitle2")
                .parent(parentFind)
                .build();

        CategorySaveRequest req = new CategorySaveRequest(2L, parentFind.getId(), "categoryNewTitle2");
        //when

        when(repository.findById(1L)).thenReturn(Optional.of(parentFind));
        when(repository.findById(2L)).thenReturn(Optional.of(find));
        when(repository.save(captor.capture())).thenReturn(expected);
        Category returned = underTest.save(req);

        //then
        verify(repository, times(1)).save(any());
        Category captured = captor.getValue();
        assertNotNull(captured);

        assertEquals(returned, expected);
        assertEquals(expected, captured);
    }

    @Test
    @DisplayName("save non root to root")
    void saveNonRootToRoot() {
        //given
        Category parentFind = Category.builder()
                .id(1L)
                .title("category1")
                .parent(null)
                .build();

        Category find = Category.builder()
                .id(2L)
                .title("category2")
                .parent(parentFind)
                .build();

        Category expected = Category.builder()
                .id(2L)
                .title("categoryNewTitle2")
                .parent(null)
                .build();

        CategorySaveRequest req = new CategorySaveRequest(2L, null, "categoryNewTitle2");
        //when

        when(repository.findById(1L)).thenReturn(Optional.of(parentFind));
        when(repository.findById(2L)).thenReturn(Optional.of(find));
        when(repository.save(captor.capture())).thenReturn(expected);
        Category returned = underTest.save(req);

        //then
        verify(repository, times(1)).save(any());
        Category captured = captor.getValue();
        assertNotNull(captured);

        assertEquals(returned, expected);
        assertEquals(expected, captured);
    }

    @Test
    @DisplayName("save root to non root")
    void saveRootToNonRoot() {
        //given
        Category parentFind = Category.builder()
                .id(1L)
                .title("category1")
                .parent(null)
                .build();

        Category find = Category.builder()
                .id(2L)
                .title("category2")
                .parent(null)
                .build();

        Category expected = Category.builder()
                .id(2L)
                .title("categoryNewTitle2")
                .parent(parentFind)
                .build();

        CategorySaveRequest req = new CategorySaveRequest(2L, parentFind.getId(), "categoryNewTitle2");
        //when

        when(repository.findById(1L)).thenReturn(Optional.of(parentFind));
        when(repository.findById(2L)).thenReturn(Optional.of(find));
        when(repository.save(captor.capture())).thenReturn(expected);
        Category returned = underTest.save(req);

        //then
        verify(repository, times(1)).save(any());
        Category captured = captor.getValue();
        assertNotNull(captured);

        assertEquals(returned, expected);
        assertEquals(expected, captured);
    }

}
