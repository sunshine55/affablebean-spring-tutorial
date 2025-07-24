package sunshine55.tutorial.afbb.ws.dao;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import sunshine55.tutorial.afbb.ws.entity.ItemEntity;

@MongoRepository
public interface ItemDao extends CrudRepository<ItemEntity, String> {
}
