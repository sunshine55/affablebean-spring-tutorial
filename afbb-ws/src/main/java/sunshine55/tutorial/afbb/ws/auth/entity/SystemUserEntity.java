package sunshine55.tutorial.afbb.ws.auth.entity;

import java.time.LocalDateTime;
import java.util.List;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedEntity("system_user")
@Getter
@Setter
@EqualsAndHashCode
public class SystemUserEntity {
    @Id
    @GeneratedValue
    private String id;

    private String name;
    private String email;
    private String username;
    private String password;
    private Boolean active;
    private List<String> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;
}
