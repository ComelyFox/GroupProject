package strategy;

import base.Bus;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class QuickSortStrategy implements SortingStrategy {

    @Override
    public void sort(List<Bus> busList) {
        if (busList == null || busList.size() <= 1) {
            return;
        }

        quickSort(busList, 0, busList.size() - 1, getComparator());
    }

    private void quickSort(List<Bus> busList, int low, int high, Comparator<Bus> comparator) {
        if (low < high) {
            int pivotIndex = partition(busList, low, high, comparator);

            quickSort(busList, low, pivotIndex - 1, comparator);
            quickSort(busList, pivotIndex + 1, high, comparator);
        }
    }

    private int partition(List<Bus> busList, int low, int high, Comparator<Bus> comparator) {
        int middle = low + (high - low) / 2;
        Bus pivot = busList.get(middle);

        Bus temp = busList.get(middle);
        busList.set(middle, busList.get(high));
        busList.set(high, temp);

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(busList.get(j), pivot) < 0) {
                i++;
                Collections.swap(busList, i, j);
            }
        }

        Collections.swap(busList, i + 1, high);

        return i + 1;
    }


    abstract Comparator<Bus> getComparator();
}
