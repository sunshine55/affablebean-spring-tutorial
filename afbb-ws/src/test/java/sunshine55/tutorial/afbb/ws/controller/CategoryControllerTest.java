package sunshine55.tutorial.afbb.ws.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import sunshine55.tutorial.afbb.ws.dao.CategoryDao;
import sunshine55.tutorial.afbb.ws.entity.CategoryEntity;
import sunshine55.tutorial.afbb.ws.service.InstanceCreator;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {
    @Mock private CategoryDao categoryDao;
    @Mock private InstanceCreator instanceCreator;

    @InjectMocks
    private CategoryController categoryController;

    @Nested
    public class GetTest {

        @Test
        @DisplayName("Get all categories")
        void test1() {
            CategoryEntity cat1 = new CategoryEntity();
            CategoryEntity cat2 = new CategoryEntity();
            when(categoryDao.findAll()).thenReturn(List.of(cat1, cat2));
            List<CategoryEntity> categories = categoryController.get("");
            assertEquals(2, categories.size());
            assertSame(cat1, categories.get(0));
            assertSame(cat2, categories.get(1));
        }

        @Test
        @DisplayName("Get category by id - found")
        void test2() {
            CategoryEntity cat = new CategoryEntity();
            when(categoryDao.findById("abc")).thenReturn(Optional.of(cat));
            List<CategoryEntity> resultList = categoryController.get("abc");
            assertEquals(1, resultList.size());
            assertSame(cat, resultList.getFirst());
        }

        @Test
        @DisplayName("Get category by id - not found")
        void test3() {
            CategoryEntity defaultCat = new CategoryEntity();
            when(categoryDao.findById("xyz")).thenReturn(Optional.empty());
            when(instanceCreator.initCategory()).thenReturn(defaultCat);
            List<CategoryEntity> resultList = categoryController.get("xyz");
            assertEquals(1, resultList.size());
            assertSame(defaultCat, resultList.getFirst());
        }
    }

    @Nested
    public class UpsertTest {

        @Test
        @DisplayName("Upsert new categories (no id)")
        void test1() {
            CategoryEntity inputCat = new CategoryEntity();
            CategoryEntity newCat = new CategoryEntity();
            CategoryEntity savedCat = new CategoryEntity();
            when(instanceCreator.initCategory()).thenReturn(newCat);
            // Simulate modifyBy is called on newCat, then saved
            when(categoryDao.saveAll(List.of(newCat))).thenReturn(List.of(savedCat));

            List<CategoryEntity> result = categoryController.upsert(List.of(inputCat));
            assertEquals(1, result.size());
            assertEquals(CategoryEntity.class, result.get(0).getClass());
        }

        @Test
        @DisplayName("Upsert existing category (with id, found)")
        void test2() {
            CategoryEntity inputCat = new CategoryEntity();
            inputCat.setId("id1");
            CategoryEntity existingCat = new CategoryEntity();
            existingCat.setId("id1");
            CategoryEntity savedCat = new CategoryEntity();
            savedCat.setId("id1");

            when(categoryDao.findById("id1")).thenReturn(Optional.of(existingCat));
            when(categoryDao.saveAll(List.of(existingCat))).thenReturn(List.of(savedCat));

            List<CategoryEntity> result = categoryController.upsert(List.of(inputCat));
            assertEquals(1, result.size());
            assertEquals("id1", result.get(0).getId());
        }

        @Test
        @DisplayName("Upsert category (with id, not found)")
        void test3() {
            CategoryEntity inputCat = new CategoryEntity();
            inputCat.setId("id2");
            CategoryEntity newCat = new CategoryEntity();
            newCat.setId("id2");
            CategoryEntity savedCat = new CategoryEntity();
            savedCat.setId("id2");

            when(categoryDao.findById("id2")).thenReturn(Optional.empty());
            when(instanceCreator.initCategory()).thenReturn(newCat);
            when(categoryDao.saveAll(List.of(newCat))).thenReturn(List.of(savedCat));

            List<CategoryEntity> result = categoryController.upsert(List.of(inputCat));
            assertEquals(1, result.size());
            assertEquals("id2", result.get(0).getId());
        }
    }

    @Nested
    public class DeleteTest {

        @Test
        @DisplayName("Delete all categories when id is null")
        void test1() {
            categoryController.delete(null);
            // Verify that deleteAll is called
            verify(categoryDao).deleteAll();
        }

        @Test
        @DisplayName("Delete all categories when id is empty")
        void test2() {
            categoryController.delete("");
            verify(categoryDao).deleteAll();
        }

        @Test
        @DisplayName("Delete category by id")
        void test3() {
            categoryController.delete("abc");
            verify(categoryDao).deleteById("abc");
        }
    }
}
