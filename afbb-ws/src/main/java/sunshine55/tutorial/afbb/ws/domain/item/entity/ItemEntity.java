package sunshine55.tutorial.afbb.ws.domain.item.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.core.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedEntity("item")
@Getter @Setter
@EqualsAndHashCode
public class ItemEntity {
    @Id
    @GeneratedValue
    private String id;

    private String categoryId, description, imageSrc, name;
    private Double price;

    public void modifyBy(ItemEntity item) {
        this.categoryId = item.categoryId;
        if (StringUtils.hasText(item.description)) {
            this.description = item.description;
        }
        if (StringUtils.hasText(item.imageSrc)) {
            this.imageSrc = item.imageSrc;
        }
        if (StringUtils.hasText(item.name)) {
            this.name = item.name;
        }
        if (item.price != null) {
            this.price = item.price;
        }
    }
}
