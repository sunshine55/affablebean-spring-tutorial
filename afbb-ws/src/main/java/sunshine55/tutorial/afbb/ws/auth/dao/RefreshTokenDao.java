package sunshine55.tutorial.afbb.ws.auth.dao;

import java.util.Optional;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import sunshine55.tutorial.afbb.ws.auth.entity.RefreshTokenEntity;

@MongoRepository
public interface RefreshTokenDao extends CrudRepository<RefreshTokenEntity, String> {
    Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);
}
