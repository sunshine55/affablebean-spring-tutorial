package sunshine55.tutorial.afbb.api.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import sunshine55.tutorial.afbb.api.entity.CategoryEntity;

@Repository
public interface CategoryDao extends ReactiveMongoRepository<CategoryEntity, String> {
    // No additional methods are needed for basic CRUD operations
    // The ReactiveMongoRepository interface provides all the necessary methods
}
