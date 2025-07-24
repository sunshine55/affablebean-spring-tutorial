package sunshine55.tutorial.afbb.ws.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import sunshine55.tutorial.afbb.ws.dao.ItemDao;
import sunshine55.tutorial.afbb.ws.entity.ItemEntity;
import sunshine55.tutorial.afbb.ws.service.InstanceCreator;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {
    @Mock private ItemDao itemDao;
    @Mock private InstanceCreator instanceCreator;

    @InjectMocks private ItemController itemController;

    @Nested
    public class GetTest {
        @Test
        @DisplayName("Get all items")
        void test1() {
            ItemEntity item1 = new ItemEntity();
            ItemEntity item2 = new ItemEntity();
            when(itemDao.findAll()).thenReturn(List.of(item1, item2));
            
            List<ItemEntity> items = itemController.get("");
            assertEquals(2, items.size());
            assertSame(item1, items.getFirst());
            assertSame(item2, items.getLast());
        }

        @Test
        @DisplayName("Get item by id - found")
        void test2() {
            ItemEntity item = new ItemEntity();
            when(itemDao.findById("abc")).thenReturn(Optional.of(item));
            
            List<ItemEntity> resultList = itemController.get("abc");
            assertEquals(1, resultList.size());
            assertSame(item, resultList.getFirst());
        }

        @Test
        @DisplayName("Get item by id - not found")
        void test3() {
            when(itemDao.findById("xyz")).thenReturn(Optional.empty());
            List<ItemEntity> resultList = itemController.get("xyz");
            assertTrue(resultList.isEmpty());
        }
    }

    @Test
    public void getByCategoryId() {
        String categoryId = "cat123";
        ItemEntity item1 = new ItemEntity();
        ItemEntity item2 = new ItemEntity();
        when(itemDao.findByCategoryId(categoryId)).thenReturn(List.of(item1, item2));

        List<ItemEntity> items = itemController.getByCategoryId(categoryId);
        assertEquals(2, items.size());
        assertSame(item1, items.getFirst());
        assertSame(item2, items.getLast());
    }

    @Nested
    public class UpsertTest {
        @Test
        @DisplayName("Upsert new items (no id)")
        void test1() {
            ItemEntity inputItem = new ItemEntity();
            ItemEntity newItem = new ItemEntity();
            ItemEntity savedItem = new ItemEntity();
            
            when(instanceCreator.initItem()).thenReturn(newItem);
            when(itemDao.saveAll(List.of(newItem))).thenReturn(List.of(savedItem));

            List<ItemEntity> result = itemController.upsert(List.of(inputItem));
            assertEquals(1, result.size());
            assertEquals(newItem, savedItem);
        }

        @Test
        @DisplayName("Upsert existing item (with id, found)")
        void test2() {
            ItemEntity inputItem = new ItemEntity();
            inputItem.setId("id1");
            ItemEntity existingItem = new ItemEntity();
            existingItem.setId("id1");
            ItemEntity savedItem = new ItemEntity();
            savedItem.setId("id1");
            
            when(itemDao.findById("id1")).thenReturn(Optional.of(existingItem));
            when(itemDao.saveAll(List.of(existingItem))).thenReturn(List.of(savedItem));

            List<ItemEntity> result = itemController.upsert(List.of(inputItem));
            assertEquals(1, result.size());
            assertEquals("id1", result.getFirst().getId());
        }

        @Test
        @DisplayName("Upsert item (with id, not found)")
        void test3() {
            ItemEntity inputItem = new ItemEntity();
            inputItem.setId("id2");
            ItemEntity newItem = new ItemEntity();
            newItem.setId("id2");
            ItemEntity savedItem = new ItemEntity();
            savedItem.setId("id2");
            
            when(itemDao.findById("id2")).thenReturn(Optional.empty());
            when(instanceCreator.initItem()).thenReturn(newItem);
            when(itemDao.saveAll(List.of(newItem))).thenReturn(List.of(savedItem));

            List<ItemEntity> result = itemController.upsert(List.of(inputItem));
            assertEquals(1, result.size());
            assertEquals("id2", result.getFirst().getId());
        }
    }

    @Nested
    public class DeleteTest {

        @Test
        @DisplayName("Delete all items")
        void test1() {
            itemController.delete(null);
            verify(itemDao).deleteAll();
        }

        @Test
        @DisplayName("Delete item by id")
        void test2() {
            itemController.delete("abc");
            verify(itemDao).deleteById("abc");
        }
    }
}
