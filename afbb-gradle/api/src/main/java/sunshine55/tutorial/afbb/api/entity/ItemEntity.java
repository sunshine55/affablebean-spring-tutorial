package sunshine55.tutorial.afbb.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "item")
@Getter @Setter
@EqualsAndHashCode
public class ItemEntity {
    @Id private String id;

    private String categoryId, description, name;
    private Double price;

    public void modifyBy(ItemEntity item) {
        this.description = item.description;
        this.name = item.name;
        this.price = item.price;
    }
}
