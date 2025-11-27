package strategy;

import base.Bus;
import base.CollectionFillType;

import java.util.List;

public class DataContext {
    private DataInputStrategy strategy;

    public void setStrategy(DataInputStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(CollectionFillType type, int size) {
        switch (type) {
            case RANDOM:
                this.strategy = new RandomInputStrategy(size);
                break;
            case FROM_FILE:
                this.strategy = new FileInputStrategy();
                break;
            case MANUAL:
                this.strategy = new ManualInputStrategy(size);
                break;
            default:
                throw new IllegalArgumentException("Неизвестная стратегия: " + type);
        }
    }

    public List<Bus> executeStrategy() {
        if (strategy == null) {
            throw new IllegalStateException("Стратегия не выбрана!");
        }
        return strategy.getBuses();
    }
}
