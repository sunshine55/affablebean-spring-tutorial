package sunshine55.tutorial.afbb.api.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import sunshine55.tutorial.afbb.api.dto.ItemDto;

@Document(collection = "category")
@Getter @Setter
@EqualsAndHashCode
public class CategoryEntity {
    @Id private String id;

    private String name, description;

    private List<ItemDto> items;
}
