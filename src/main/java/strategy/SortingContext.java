package strategy;

import base.Bus;
import base.SortType;
import sorting.BusQuickSorter;
import sorting.BusTrickySorter;

import java.util.List;

public class SortingContext {
    private SortingStrategy strategy;

    public void setStrategy(SortType sortType) {
        this.strategy = switch (sortType) {
            case QUICK_SORT -> new BusQuickSorter();
            case TRICKY_SORT -> new BusTrickySorter();
        };
    }

    public void executeSort(List<Bus> buses) {
        if (strategy == null) {
            throw new IllegalStateException("Стратегия не установлена!");
        }
        strategy.sort(buses);
    }
}
