package sunshine55.tutorial.afbb.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "item")
@Getter @Setter
@EqualsAndHashCode
public class ItemEntity {
    @Id private String id;

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
