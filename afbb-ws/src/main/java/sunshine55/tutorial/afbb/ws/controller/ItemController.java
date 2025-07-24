package sunshine55.tutorial.afbb.ws.controller;

import java.util.Collections;
import java.util.List;

import io.micronaut.core.util.StringUtils;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import lombok.RequiredArgsConstructor;
import sunshine55.tutorial.afbb.ws.dao.ItemDao;
import sunshine55.tutorial.afbb.ws.entity.ItemEntity;
import sunshine55.tutorial.afbb.ws.service.InstanceCreator;

@Controller("/items")
@RequiredArgsConstructor
public class ItemController {
    private final InstanceCreator instanceCreator;
    private final ItemDao itemDao;

    @Get
    public List<ItemEntity> get(@QueryValue(value = "id", defaultValue = "") String id) {
        if (!StringUtils.hasText(id)) {
            return itemDao.findAll();
        }
        ItemEntity found = itemDao.findById(id).orElse(null);
        if (found == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(found);
    }

    @Post
    public List<ItemEntity> upsert(@Body List<ItemEntity> items) {
        List<ItemEntity> nextItems = items.stream().map(item -> {
            String id = item.getId();
            if (!StringUtils.hasText(id)) {
                ItemEntity nextItem = instanceCreator.initItem();
                nextItem.modifyBy(item);
                return nextItem;
            }
            ItemEntity existingItem = itemDao
                .findById(id)
                .orElse(instanceCreator.initItem());
            existingItem.modifyBy(item);
            return existingItem;
        }).toList();
        return itemDao.saveAll(nextItems);
    }

    @Delete
    public void delete(@QueryValue(value = "id", defaultValue = "") String id) {
        if (!StringUtils.hasText(id)) {
            itemDao.deleteAll();
            return;
        }
        itemDao.deleteById(id);
    }
}
