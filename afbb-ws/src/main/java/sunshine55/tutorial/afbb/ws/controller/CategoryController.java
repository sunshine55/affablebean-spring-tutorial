package sunshine55.tutorial.afbb.ws.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import lombok.RequiredArgsConstructor;
import sunshine55.tutorial.afbb.ws.dao.CategoryDao;
import sunshine55.tutorial.afbb.ws.entity.CategoryEntity;
import sunshine55.tutorial.afbb.ws.service.InstanceCreator;

@Controller("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryDao categoryDao;
    private final InstanceCreator instanceCreator;

    @Get
    public List<CategoryEntity> get(@QueryValue(value = "id", defaultValue = "") String id) {
        if (!StringUtils.hasText(id)) {
            return categoryDao.findAll();
        }
        CategoryEntity found = categoryDao.findById(id).orElse(null);
        if (found == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(found);
    }

    @Post
    public List<CategoryEntity> upsert(@Body List<CategoryEntity> categories) {
        List<CategoryEntity> toInsertList = new ArrayList<>(categories.size());
        List<CategoryEntity> toUpdateList = new ArrayList<>(categories.size());
        for (CategoryEntity category : categories) {
            if (!StringUtils.hasText(category.getId())) {
                toInsertList.add(category);
            } else {
                Optional<CategoryEntity> found = categoryDao.findById(category.getId());
                if (found.isEmpty()) {
                    CategoryEntity newCategory = instanceCreator.initCategory();
                    newCategory.modifyBy(category);
                    toInsertList.add(category);
                } else {
                    CategoryEntity existingCategory = found.get();
                    existingCategory.modifyBy(category);
                    toUpdateList.add(existingCategory);
                }
            }
        }
        List<CategoryEntity> result = new ArrayList<>(categories.size());
        if (!toInsertList.isEmpty()) {
            result.addAll(categoryDao.saveAll(toInsertList));
        }
        if (!toUpdateList.isEmpty()) {
            result.addAll(categoryDao.updateAll(toUpdateList));
        }
        return result;
    }

    @Delete
    public void delete(@QueryValue(value = "id", defaultValue = "") String id) {
        if (!StringUtils.hasText(id)) {
            categoryDao.deleteAll();
            return;
        }
        categoryDao.deleteById(id);
    }   
}
