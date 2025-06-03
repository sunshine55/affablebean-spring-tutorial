package sunshine55.tutorial.afbb.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

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
        if (StringUtils.hasText(category.description)) {
            this.description = category.description;
        }
        if (StringUtils.hasText(category.imageSrc)) {
            this.imageSrc = category.imageSrc;
        }
        if (StringUtils.hasText(category.name)) {
            this.name = category.name;
        }
    }
}
