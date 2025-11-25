package strategy;

import base.Bus;

import java.util.Comparator;

public class QuickSortByMileAgeStrategy extends QuickSortStrategy{

    @Override
    Comparator<Bus> getComparator() {
        return Comparator.comparing(Bus::getMileage);
    }
}
