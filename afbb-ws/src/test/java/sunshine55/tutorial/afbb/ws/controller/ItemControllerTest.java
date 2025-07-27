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
import sunshine55.tutorial.afbb.ws.dao.ItemDao;
import sunshine55.tutorial.afbb.ws.entity.ItemEntity;
import sunshine55.tutorial.afbb.ws.service.InstanceCreator;

@MicronautTest
public class ItemControllerTest {
    // Dependencies
    @Inject ItemDao itemDao;
    @Inject InstanceCreator instanceCreator;
    // Mocking dependencies
    @MockBean(ItemDao.class)
    ItemDao itemDao() {
        return mock(ItemDao.class);
    }
    @MockBean(InstanceCreator.class)
    InstanceCreator instanceCreator() {
        return mock(InstanceCreator.class);
    }
    // Class under test
    @Inject ItemController itemController;

    @Nested
    public class GetTest {

        @Test
        @DisplayName("Get all items")
        public void test1() {
            List<ItemEntity> items = new ArrayList<>();
            items.add(new ItemEntity());
            when(itemDao.findAll()).thenReturn(items);
            List<ItemEntity> result = itemController.get(null);
            assertSame(items, result);
        }

        @Test
        @DisplayName("Get item by id (found)")
        public void test2() {
            ItemEntity found = new ItemEntity();
            when(itemDao.findById("abc")).thenReturn(Optional.of(found));
            List<ItemEntity> result = itemController.get("abc");
            assertEquals(1, result.size());
            assertEquals(found, result.getFirst());
        }

        @Test
        @DisplayName("Get item by id (not found)")
        public void test3() {
            when(itemDao.findById("abc")).thenReturn(Optional.empty());
            List<ItemEntity> result = itemController.get("abc");
            assertTrue(result.isEmpty());
        }
    }

    @Nested
    public class UpsertTest {
        @Inject ResourceLoader resourceLoader;
        @Inject ObjectMapper objectMapper;

        private List<ItemEntity> existingItems;

        @BeforeEach
        public void setUp() throws IOException {
            InputStream inputStream = resourceLoader
                .getResourceAsStream("existingItems.json")
                .orElse(null);
            existingItems = objectMapper
                .readValue(inputStream, Argument.listOf(ItemEntity.class));
        }

        @Test
        @DisplayName("Insert items")
        public void test1() {
            // all new items
            existingItems.forEach(item -> item.setId(null));
            // second item id is not found
            existingItems.getFirst().setId("some-id");
            when(itemDao.findById("some-id")).thenReturn(Optional.empty());
            when(instanceCreator.initItem()).thenReturn(new ItemEntity());
            // execute test and verify
            when(itemDao.saveAll(anyList())).thenReturn(existingItems);
            List<ItemEntity> result = itemController.upsert(existingItems);
            assertEquals(16, result.size());
            verify(itemDao).saveAll(anyList());
            verify(itemDao, times(0)).updateAll(anyList());
        }

        @Test
        @DisplayName("Update items")
        public void test2() {
            existingItems.forEach(item -> {
                when(itemDao.findById(item.getId())).thenReturn(Optional.of(item));
            });
            when(itemDao.updateAll(anyList())).thenReturn(existingItems);
            List<ItemEntity> result = itemController.upsert(existingItems);
            assertEquals(16, result.size());
            verify(itemDao, times(0)).saveAll(anyList());
            verify(itemDao).updateAll(anyList());
        }
    }

    @Nested
    public class DeleteTest {

        @Test
        @DisplayName("Delete all items")
        public void test1() {
            itemController.delete(null);
            verify(itemDao).deleteAll();
        }

        @Test
        @DisplayName("Delete item by id")
        public void test2() {
            itemController.delete("abc");
            verify(itemDao).deleteById("abc");
        }
    }
}
