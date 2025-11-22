package service.fillCollection.fillStrategy.manual;

import service.fillCollection.fillStrategy.IFillCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FillManualStrategy implements IFillCollection {
    private final HashMap<String, Object> map;
    private final List<HashMap<String, Object>> collection;
    private final String number;
    private final String model;
    private final int mileage;

    public FillManualStrategy(String number, String model, int mileage) {
        this.number = number;
        this.model = model;
        this.mileage = mileage;
        this.map = new HashMap<>();
        this.collection = new ArrayList<>();
    }

    private List<HashMap<String, Object>> fillCollectionManual() {
        map.put("number", this.number);
        map.put("model", this.model);
        map.put("mileage", this.mileage);
        collection.add(new HashMap<>(map));
        return collection;
    }

    @Override
    public List<HashMap<String, Object>> getFillCollection() {
        return fillCollectionManual();
    }
}
