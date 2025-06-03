package sunshine55.tutorial.afbb.api.controller;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sunshine55.tutorial.afbb.api.dao.CategoryDao;
import sunshine55.tutorial.afbb.api.entity.CategoryEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryDao categoryDao;

    @GetMapping
    public Flux<CategoryEntity> get() {
        return categoryDao.findAll();
    }

    @GetMapping("/{id}")
    public Mono<CategoryEntity> getById(@PathVariable String id) {
        return categoryDao.findById(id);
    }
    

    @PostMapping
    public Flux<CategoryEntity> upsert(@RequestBody List<CategoryEntity> categories) {
        return Flux
            .fromIterable(categories)
            .flatMap(category -> {
                String id = category.getId();
                if (StringUtils.hasText(id)) {
                    return categoryDao.findById(id).flatMap(existingCategory -> {
                        existingCategory.modifyBy(category);
                        return categoryDao.save(existingCategory);
                    });
                }
                CategoryEntity newCategory = new CategoryEntity();
                newCategory.modifyBy(category);
                return categoryDao.save(newCategory);
            });
    }

    @DeleteMapping
    public Mono<Void> delete(@RequestParam(required = false) String id) {
        if (StringUtils.hasText(id)) {
            return categoryDao.deleteById(id);
        }
        return categoryDao.deleteAll();
    }
}
