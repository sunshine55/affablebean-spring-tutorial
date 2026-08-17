package sunshine55.tutorial.afbb.ws.domain.category.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedEntity("category")
@Getter @Setter
@EqualsAndHashCode
public class CategoryEntity {
    @Id
    @GeneratedValue
    private String id;

    private String description, imageSrc, name;

    public void modifyBy(CategoryEntity nextCategory) {
        this.name = nextCategory.getName();
        this.description = nextCategory.getDescription();
        this.imageSrc = nextCategory.getImageSrc();
    }
}
