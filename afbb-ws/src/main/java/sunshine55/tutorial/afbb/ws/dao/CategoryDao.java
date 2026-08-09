package sunshine55.tutorial.afbb.ws.dao;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import sunshine55.tutorial.afbb.ws.entity.CategoryEntity;

@MongoRepository
public interface CategoryDao extends CrudRepository<CategoryEntity, String> {
}

