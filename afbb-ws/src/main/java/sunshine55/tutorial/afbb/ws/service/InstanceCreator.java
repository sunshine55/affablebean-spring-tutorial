package sunshine55.tutorial.afbb.ws.service;

import jakarta.inject.Singleton;
import sunshine55.tutorial.afbb.ws.entity.CategoryEntity;
import sunshine55.tutorial.afbb.ws.entity.ItemEntity;

@Singleton
public class InstanceCreator {
    
    public CategoryEntity initCategory() {
        return new CategoryEntity();
    }

    public ItemEntity initItem() {
        return new ItemEntity();
    }
}
