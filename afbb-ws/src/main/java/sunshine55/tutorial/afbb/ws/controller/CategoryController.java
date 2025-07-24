package sunshine55.tutorial.afbb.ws.controller;

import java.util.Collections;
import java.util.List;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Post;
import lombok.RequiredArgsConstructor;
import sunshine55.tutorial.afbb.ws.dao.CategoryDao;
import sunshine55.tutorial.afbb.ws.entity.CategoryEntity;
import sunshine55.tutorial.afbb.ws.service.InstanceCreator;

@Controller("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryDao categoryDao;
    private final InstanceCreator instanceCreator;

    @Get("/{id}")
    public List<CategoryEntity> get(String id) {
        if (id == null || id.isEmpty()) {
            return categoryDao.findAll();
        }
        return Collections.singletonList(
            categoryDao.findById(id).orElse(instanceCreator.initCategory())
        );
    }

    @Post
    public List<CategoryEntity> upsert(List<CategoryEntity> categories) {
        List<CategoryEntity> nextCategories = categories.stream().map(category -> {
            String id = category.getId();
            if (id == null || id.isEmpty()) {
                CategoryEntity nextCategory = instanceCreator.initCategory();
                nextCategory.modifyBy(category);
                return nextCategory;
            }
            CategoryEntity existingCategory = categoryDao.findById(id).orElse(null);
            if (existingCategory == null) {
                existingCategory = instanceCreator.initCategory();
            }
            existingCategory.modifyBy(category);
            return existingCategory;
        }).toList();
        return categoryDao.saveAll(nextCategories);
    }

    @Delete("/{id}")
    public void delete(String id) {
        if (id == null || id.isEmpty()) {
            categoryDao.deleteAll();
            return;
        }
        categoryDao.deleteById(id);
    }   
}
