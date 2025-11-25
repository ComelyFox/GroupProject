package service.fillCollection.fillStrategy.manual;

import service.fillCollection.fillStrategy.IFillCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FillManualStrategy implements IFillCollection {
    private final HashMap<String, Object> mapEntityBus;
    private final List<HashMap<String, Object>> listMapEntityBus;

    public FillManualStrategy() {
        this.mapEntityBus = new HashMap<>();
        this.listMapEntityBus = new ArrayList<>();
    }

    public void setBus(String model, int number, int mileage) {
        mapEntityBus.put("model", model);
        mapEntityBus.put("number", number);
        mapEntityBus.put("mileage", mileage);
        listMapEntityBus.add(new HashMap<>(mapEntityBus));
    }

    @Override
    public List<HashMap<String, Object>> fillCollection() {
        return listMapEntityBus;
    }

    @Override
    public void clearStrategyCollection() {
        mapEntityBus.clear();
        listMapEntityBus.clear();
    }
}
