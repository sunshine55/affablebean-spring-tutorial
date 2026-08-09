package sunshine55.tutorial.afbb.ws.controller;

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

import io.micronaut.core.io.ResourceLoader;
import io.micronaut.core.type.Argument;
import io.micronaut.serde.ObjectMapper;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import sunshine55.tutorial.afbb.ws.dao.CategoryDao;
import sunshine55.tutorial.afbb.ws.entity.CategoryEntity;
import sunshine55.tutorial.afbb.ws.service.InstanceCreator;

@MicronautTest
public class CategoryControllerTest {
    // Dependencies
    @Inject CategoryDao categoryDao;
    @Inject InstanceCreator instanceCreator;
    // Mocking dependencies
    @MockBean(CategoryDao.class)
    CategoryDao categoryDao() {
        return mock(CategoryDao.class);
    }
    @MockBean(InstanceCreator.class)
    InstanceCreator instanceCreator() {
        return mock(InstanceCreator.class);
    }
    // Class under test
    @Inject CategoryController categoryController;

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
        @Inject ResourceLoader resourceLoader;
        @Inject ObjectMapper objectMapper;

        private List<CategoryEntity> existingCategories;

        @BeforeEach
        public void setUp() throws IOException {
            InputStream inputStream = resourceLoader
                .getResourceAsStream("existingCategories.json")
                .orElse(null);
            existingCategories = objectMapper
                .readValue(inputStream, Argument.listOf(CategoryEntity.class));
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

