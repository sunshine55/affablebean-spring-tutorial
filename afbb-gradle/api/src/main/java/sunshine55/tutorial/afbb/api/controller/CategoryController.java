package sunshine55.tutorial.afbb.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import sunshine55.tutorial.afbb.api.dao.CategoryDao;
import sunshine55.tutorial.afbb.api.entity.CategoryEntity;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryDao categoryDao;

    @GetMapping("/getAll")
    public Flux<CategoryEntity> getAll() {
        return categoryDao.findAll();
    }
}

