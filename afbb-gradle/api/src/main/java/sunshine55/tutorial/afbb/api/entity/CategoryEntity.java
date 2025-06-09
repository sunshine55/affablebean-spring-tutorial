package sunshine55.tutorial.afbb.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "category")
@Getter @Setter
@EqualsAndHashCode
public class CategoryEntity {
    @Id private String id;

    private String description, imageSrc, name;
    
    public void modifyBy(CategoryEntity category) {
        this.name = category.getName();
        this.description = category.getDescription();
        this.imageSrc = category.getImageSrc();
    }
}
