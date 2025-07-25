package sunshine55.tutorial.afbb.ws.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Get("/category")
    public List<ItemEntity> getByCategoryId(@QueryValue String categoryId) {
        return itemDao.findByCategoryId(categoryId);
    }

    @Post
    public List<ItemEntity> upsert(@Body List<ItemEntity> items) {
        List<ItemEntity> toInsertList = new ArrayList<>(items.size());
        List<ItemEntity> toUpdateList = new ArrayList<>(items.size());
        for (ItemEntity item : items) {
            if (!StringUtils.hasText(item.getId())) {
                toInsertList.add(item);
            } else {
                Optional<ItemEntity> found = itemDao.findById(item.getId());
                if (found.isEmpty()) {
                    ItemEntity newCategory = instanceCreator.initItem();
                    newCategory.modifyBy(item);
                    toInsertList.add(item);
                } else {
                    ItemEntity existingCategory = found.get();
                    existingCategory.modifyBy(item);
                    toUpdateList.add(existingCategory);
                }
            }
        }
        List<ItemEntity> result = new ArrayList<>(items.size());
        if (!toInsertList.isEmpty()) {
            result.addAll(itemDao.saveAll(toInsertList));
        }
        if (!toUpdateList.isEmpty()) {
            result.addAll(itemDao.updateAll(toUpdateList));
        }
        return result;
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
