package sunshine55.tutorial.afbb.ws.domain.item.dao;

import java.util.List;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import sunshine55.tutorial.afbb.ws.domain.item.entity.ItemEntity;

@MongoRepository
public interface ItemDao extends CrudRepository<ItemEntity, String> {

    List<ItemEntity> findByCategoryId(String categoryId);
}
