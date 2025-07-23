package sunshine55.tutorial.afbb.ws.controller;

import java.util.Collections;
import java.util.List;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.RequiredArgsConstructor;
import sunshine55.tutorial.afbb.ws.dao.CategoryDao;
import sunshine55.tutorial.afbb.ws.entity.CategoryEntity;

@Controller("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryDao categoryDao;

    @Get("/{id}")
    public List<CategoryEntity> get(String id) {
        if (id == null || id.isEmpty()) {
            return categoryDao.findAll();
        }
        return Collections.singletonList(
            categoryDao.findById(id).orElse(new CategoryEntity())
        );
    }
}
