package sunshine55.tutorial.afbb.api.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import sunshine55.tutorial.afbb.api.entity.ItemEntity;

@Repository
public interface ItemDao extends ReactiveMongoRepository<ItemEntity, String> {
    
    Flux<ItemEntity> findByCategoryId(String categoryId);
}
