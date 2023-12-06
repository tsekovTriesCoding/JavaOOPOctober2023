package bakery.repositories.classes;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.repositories.interfaces.FoodRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FoodRepositoryImpl implements FoodRepository<BakedFood> {
    private Map<String, BakedFood> models;

    public FoodRepositoryImpl() {
        this.models = new HashMap<>();
    }

    @Override
    public BakedFood getByName(String name) {
        return this.models.get(name);
    }

    @Override
    public Collection<BakedFood> getAll() {
        return Collections.unmodifiableCollection(this.models.values());
    }

    @Override
    public void add(BakedFood bakedFood) {
        this.models.put(bakedFood.getName(), bakedFood);
    }
}
