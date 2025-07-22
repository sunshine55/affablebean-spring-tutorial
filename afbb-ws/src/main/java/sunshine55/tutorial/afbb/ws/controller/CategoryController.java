package sunshine55.tutorial.afbb.ws.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.RequiredArgsConstructor;
import sunshine55.tutorial.afbb.ws.dao.CategoryDao;
import sunshine55.tutorial.afbb.ws.entity.CategoryEntity;

@Controller("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryDao categoryDao;

    @Get
    public Iterable<CategoryEntity> get() {
        return categoryDao.findAll();
    }
}
