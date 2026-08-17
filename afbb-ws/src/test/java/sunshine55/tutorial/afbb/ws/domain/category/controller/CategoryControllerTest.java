package sunshine55.tutorial.afbb.ws.domain.category.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import sunshine55.tutorial.afbb.ws.core.service.InstanceCreator;
import sunshine55.tutorial.afbb.ws.domain.category.dao.CategoryDao;
import sunshine55.tutorial.afbb.ws.domain.category.entity.CategoryEntity;

public class CategoryControllerTest {
    private CategoryDao categoryDao;
    private InstanceCreator instanceCreator;
    private CategoryController categoryController;

    @BeforeEach
    public void setUp() {
        categoryDao = mock(CategoryDao.class);
        instanceCreator = mock(InstanceCreator.class);
        categoryController = new CategoryController(categoryDao, instanceCreator);
    }

    @Nested
    public class GetTest {

        @Test
        @DisplayName("Get all categories")
        public void test1() {
            List<CategoryEntity> categories = new ArrayList<>();
            categories.add(new CategoryEntity());
            when(categoryDao.findAll()).thenReturn(categories);
            List<CategoryEntity> result = categoryController.get(null);
            assertSame(categories, result);
        }

        @Test
        @DisplayName("Get category by id (found)")
        public void test2() {
            CategoryEntity found = new CategoryEntity();
            when(categoryDao.findById("abc")).thenReturn(Optional.of(found));
            List<CategoryEntity> result = categoryController.get("abc");
            assertEquals(1, result.size());
            assertEquals(found, result.getFirst());
        }

        @Test
        @DisplayName("Get category by id (not found)")
        public void test3() {
            when(categoryDao.findById("abc")).thenReturn(Optional.empty());
            List<CategoryEntity> result = categoryController.get("abc");
            assertTrue(result.isEmpty());
        }
    }

    @Nested
    public class UpsertTest {
        private List<CategoryEntity> existingCategories;

        @BeforeEach
        public void setUp() throws IOException {
            InputStream inputStream = getClass()
                .getResourceAsStream("/json/existingCategories.json");
            ObjectMapper objectMapper = new ObjectMapper();
            existingCategories = objectMapper
                .readValue(inputStream, new TypeReference<List<CategoryEntity>>(){});
        }

        @Test
        @DisplayName("Insert categories")
        public void test1() {
            // all new categories
            existingCategories.forEach(category -> category.setId(null));
            // second category id is not found
            existingCategories.getFirst().setId("some-id");
            when(categoryDao.findById("some-id")).thenReturn(Optional.empty());
            when(instanceCreator.initCategory()).thenReturn(new CategoryEntity());
            // execute test and verify
            when(categoryDao.saveAll(anyList())).thenReturn(existingCategories);
            List<CategoryEntity> result = categoryController.upsert(existingCategories);
            assertEquals(4, result.size());
            verify(categoryDao).saveAll(anyList());
            verify(categoryDao, times(0)).updateAll(anyList());
        }

        @Test
        @DisplayName("Update categories")
        public void test2() {
            existingCategories.forEach(category -> {
                when(categoryDao.findById(category.getId())).thenReturn(Optional.of(category));
            });
            when(categoryDao.updateAll(anyList())).thenReturn(existingCategories);
            List<CategoryEntity> result = categoryController.upsert(existingCategories);
            assertEquals(4, result.size());
            verify(categoryDao, times(0)).saveAll(anyList());
            verify(categoryDao).updateAll(anyList());
        }
    }

    @Nested
    public class DeleteTest {

        @Test
        @DisplayName("Delete all categories")
        public void test1() {
            categoryController.delete(null);
            verify(categoryDao).deleteAll();
        }

        @Test
        @DisplayName("Delete category by id")
        public void test2() {
            categoryController.delete("abc");
            verify(categoryDao).deleteById("abc");
        }
    }
}
