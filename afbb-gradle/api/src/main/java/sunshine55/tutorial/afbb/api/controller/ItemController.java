package sunshine55.tutorial.afbb.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sunshine55.tutorial.afbb.api.dao.ItemDao;
import sunshine55.tutorial.afbb.api.entity.ItemEntity;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemDao itemDao;

    @GetMapping
    public Flux<ItemEntity> get(@RequestParam(required = false) String categoryId) {
        if (StringUtils.hasText(categoryId)) {
            return itemDao.findByCategoryId(categoryId);
        }
        return itemDao.findAll();
    }

    @PostMapping
    public Flux<ItemEntity> upsert(@RequestBody List<ItemEntity> items) {
        return Flux
            .fromIterable(items)
            .flatMap(item -> {
                String id = item.getId();
                if (StringUtils.hasText(id)) {
                    return itemDao.findById(id).flatMap(existingItem -> {
                        existingItem.modifyBy(item);
                        return itemDao.save(existingItem);
                    });
                }
                ItemEntity newItem = new ItemEntity();
                newItem.modifyBy(item);
                return itemDao.save(newItem);
            });
    }
    
    @DeleteMapping
    public Mono<Void> delete(@RequestParam(required = false) String id) {
        if (StringUtils.hasText(id)) {
            return itemDao.deleteById(id);
        }
        return itemDao.deleteAll();
    }
}
