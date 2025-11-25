package service.fillCollection;

import base.Bus;
import service.fillCollection.fillStrategy.IFillCollection;
import service.fillCollection.fillStrategy.file.FillFromFileStrategy;
import service.fillCollection.fillStrategy.manual.FillManualStrategy;
import service.fillCollection.fillStrategy.random.FillRandomStrategy;

import java.util.ArrayList;
import java.util.List;

public class FillBus {
    private final IFillCollection strategy;
    private static final List<Bus> busList = new ArrayList<>();
    public FillBus(IFillCollection strategy) {
        this.strategy = strategy;
    }

    public void setBus(String model, int number, int mileage) {
        if (strategy instanceof FillManualStrategy) {
            ((FillManualStrategy) strategy).setBus(model, number, mileage);
        } else {
            throw new UnsupportedOperationException("Эта стратегия не поддерживает ручной ввод значений");
        }
    }

    public void setPath(String path) {
        if (strategy instanceof FillFromFileStrategy) {
            ((FillFromFileStrategy) strategy).setPath(path);
        } else {
            throw new UnsupportedOperationException("Эта стратегия не поддерживает ввод пути до файла");
        }
    }

    public void setQuantityBus(int quantity) {
        if (strategy instanceof FillRandomStrategy) {
            ((FillRandomStrategy) strategy).setQuantityBus(quantity);
        } else {
            throw new UnsupportedOperationException("Эта стратегия не поддерживает данный метод");
        }
    }

    public void fillCollection() {
        busList.addAll(this.strategy.fillCollection().stream()
                .map(map -> new Bus.BusBuilder()
                        .setModel((String) map.get("model"))
                        .setSerialNumber((Integer) map.get("number"))
                        .setMileage((Integer) map.get("mileage"))
                        .build()).toList());
        strategy.clearStrategyCollection();//очищаем временные данные в стратегии
    }

    public List<Bus> getBusList() {
        return busList;
    }
}
