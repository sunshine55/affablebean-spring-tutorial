package sunshine55.tutorial.afbb.ws.auth.dao;

import java.util.Optional;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import sunshine55.tutorial.afbb.ws.auth.entity.SystemUserEntity;

@MongoRepository
public interface SystemUserDao extends CrudRepository<SystemUserEntity, String> {
    Optional<SystemUserEntity> findByUsername(String username);
}
