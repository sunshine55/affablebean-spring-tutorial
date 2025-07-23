package sunshine55.tutorial.afbb.ws.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import sunshine55.tutorial.afbb.ws.dao.CategoryDao;
import sunshine55.tutorial.afbb.ws.entity.CategoryEntity;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @Mock
    private CategoryDao categoryDao;

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
            when(categoryDao.findById("xyz")).thenReturn(Optional.empty());
            List<CategoryEntity> resultList = categoryController.get("xyz");
            assertEquals(1, resultList.size());
        }
    }
}
