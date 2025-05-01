package sunshine55.tutorial.afbb.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sunshine55.tutorial.afbb.api.dao.ItemDao;
import sunshine55.tutorial.afbb.api.entity.ItemEntity;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemDao itemDao;

    @GetMapping
    public Flux<ItemEntity> get(@RequestParam String categoryId) {
        return itemDao.findByCategoryId(categoryId);
    }

    @PostMapping
    public Mono<ItemEntity> upsert(@RequestBody ItemEntity item) {
        String id = item.getId();
        return itemDao
            .findById(id)
            .flatMap(existingItem -> {
                existingItem.modifyBy(item);
                return itemDao.save(existingItem);
            })
            .switchIfEmpty(Mono.defer(() -> {
                String categoryId = item.getCategoryId();
                if (!StringUtils.hasText(categoryId)) {
                    return Mono.empty();
                }
                ItemEntity newItem = new ItemEntity();
                newItem.modifyBy(item);
                newItem.setCategoryId(item.getCategoryId());
                return itemDao.save(newItem);
            }));
    }
    
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        if (StringUtils.hasText(id)) {
            return itemDao.deleteById(id);
        }
        return Mono.empty();
    }
}
