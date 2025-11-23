package sorting;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import base.Bus;
import strategy.SortingStrategy;

public class BusQuickSorter implements SortingStrategy {
    @Override
    public void sort(List<Bus> buses) {
        if (buses == null || buses.size() <= 1) return;
        quickSort(buses, 0, buses.size() - 1);
    }

    private void quickSort(List<Bus> buses, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(buses, low, high);
            quickSort(buses, low, pivotIndex - 1);
            quickSort(buses, pivotIndex + 1, high);
        }
    }

    private int partition(List<Bus> buses, int low, int high) {
        // Выбор pivot, перемещение элементов
        // Использование компаратора для сравнения
        Bus pivot = buses.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compareBuses(buses.get(j), pivot) < 0) {
                i++;
                Collections.swap(buses, i, j);
            }
        }
        Collections.swap(buses, i + 1, high);
        return i + 1;
    }

    private int compareBuses(Bus bus1, Bus bus2) {
        // Комплексное сравнение через компаратор
        return Comparator
                .comparing(Bus::getModel)
                .thenComparing(Bus::getSerialNumber)
                .thenComparing(Bus::getMileage)
                .compare(bus1, bus2);
    }
}
