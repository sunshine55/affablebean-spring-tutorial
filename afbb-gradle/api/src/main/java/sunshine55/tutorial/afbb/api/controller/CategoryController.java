package sunshine55.tutorial.afbb.api.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping
    public Mono<CategoryEntity> upsert(@RequestBody CategoryEntity category) {
        String id = category.getId();
        return categoryDao
            .findById(id)
            .flatMap(existingCategory -> {
                existingCategory.modifyBy(category);
                return categoryDao.save(existingCategory);
            })
            .switchIfEmpty(Mono.defer(() -> {
                CategoryEntity newCategory = new CategoryEntity();
                newCategory.modifyBy(category);
                return categoryDao.save(newCategory);
            }));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        if (StringUtils.hasText(id)) {
            return categoryDao.deleteById(id);
        }
        return Mono.empty();
    }
}
