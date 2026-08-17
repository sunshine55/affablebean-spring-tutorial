package sunshine55.tutorial.afbb.ws.core.service;

import jakarta.inject.Singleton;
import sunshine55.tutorial.afbb.ws.domain.category.entity.CategoryEntity;
import sunshine55.tutorial.afbb.ws.domain.item.entity.ItemEntity;

@Singleton
public class InstanceCreator {
    
    public CategoryEntity initCategory() {
        return new CategoryEntity();
    }

    public ItemEntity initItem() {
        return new ItemEntity();
    }
}
