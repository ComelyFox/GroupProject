import base.Bus;
import org.junit.jupiter.api.Test;
import strategy.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ValidAndInvalidDataTest {

    //Вспомогательный метод для создания тестовых автобусов
    private List<Bus> createTestBuses() {
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus.BusBuilder().setModel("Volvo").setSerialNumber(2).setMileage(5000).build());
        buses.add(new Bus.BusBuilder().setModel("Mercedes").setSerialNumber(1).setMileage(3000).build());
        buses.add(new Bus.BusBuilder().setModel("Volvo").setSerialNumber(1).setMileage(2000).build());
        return buses;
    }

    //Вспомогательные методы проверки сортировки
    private boolean isSortedByMileage(List<Bus> list) {
        for (int i = 1; i < list.size(); i++) {
            Bus prev = list.get(i-1);
            Bus curr = list.get(i);
            if (prev != null && curr != null && prev.getMileage() > curr.getMileage()) return false;
        }
        return true;
    }

    private boolean isSortedByModel(List<Bus> list) {
        for (int i = 1; i < list.size(); i++) {
            Bus prev = list.get(i-1);
            Bus curr = list.get(i);
            if (prev != null && curr != null && prev.getModel().compareTo(curr.getModel()) > 0) return false;
        }
        return true;
    }

    private boolean isSortedBySerialNumber(List<Bus> list) {
        for (int i = 1; i < list.size(); i++) {
            Bus prev = list.get(i-1);
            Bus curr = list.get(i);
            if (prev != null && curr != null && prev.getSerialNumber() > curr.getSerialNumber()) return false;
        }
        return true;
    }

    // Тесты на валидные данные
    @Test
    void testSortByMileage() {
        List<Bus> buses = createTestBuses();
        QuickSortStrategy strategy = new QuickSortByMileAgeStrategy();
        strategy.sort(buses);
        assertTrue(isSortedByMileage(buses));
    }

    @Test
    void testSortByModel() {
        List<Bus> buses = createTestBuses();
        QuickSortStrategy strategy = new QuickSortByModelStrategy();
        strategy.sort(buses);
        assertTrue(isSortedByModel(buses));
    }

    @Test
    void testSortBySerialNumber() {
        List<Bus> buses = createTestBuses();
        QuickSortStrategy strategy = new QuickSortByNumberStrategy();
        strategy.sort(buses);
        assertTrue(isSortedBySerialNumber(buses));
    }

    //Тесты на невалидные данные
    @Test
    void testSortNullList() {
        QuickSortStrategy strategy = new QuickSortByMileAgeStrategy();
        assertDoesNotThrow(() -> strategy.sort(null));
    }

    @Test
    void testSortEmptyList() {
        QuickSortStrategy strategy = new QuickSortByModelStrategy();
        List<Bus> empty = new ArrayList<>();
        assertDoesNotThrow(() -> strategy.sort(empty));
        assertTrue(empty.isEmpty());
    }

    @Test
    void testSortSingleElement() {
        List<Bus> single = new ArrayList<>();
        Bus bus = new Bus.BusBuilder().setModel("Test").setSerialNumber(5).setMileage(100).build();
        single.add(bus);

        QuickSortStrategy strategy = new QuickSortByNumberStrategy();
        strategy.sort(single);

        assertEquals(1, single.size());
        assertEquals(bus, single.get(0));
    }

}