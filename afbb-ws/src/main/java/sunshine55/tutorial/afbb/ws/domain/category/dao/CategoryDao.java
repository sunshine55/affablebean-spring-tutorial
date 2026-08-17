package sunshine55.tutorial.afbb.ws.domain.category.dao;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import sunshine55.tutorial.afbb.ws.domain.category.entity.CategoryEntity;

@MongoRepository
public interface CategoryDao extends CrudRepository<CategoryEntity, String> {
}
