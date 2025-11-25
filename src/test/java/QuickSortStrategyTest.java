import base.Bus;
import org.junit.jupiter.api.Test;
import strategy.QuickSortByMileAgeStrategy;
import strategy.QuickSortByModelStrategy;
import strategy.QuickSortByNumberStrategy;
import strategy.QuickSortStrategy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortStrategyTest {

    // Создание автобусов для тестов
    private List<Bus> createTestBuses() {
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus.BusBuilder().setModel("Volvo").setSerialNumber(2).setMileage(5000).build());
        buses.add(new Bus.BusBuilder().setModel("Mercedes").setSerialNumber(1).setMileage(3000).build());
        buses.add(new Bus.BusBuilder().setModel("Volvo").setSerialNumber(1).setMileage(2000).build());
        return buses;
    }

    @Test
    void testQuickSortByModel() {
        // Сортировка по модели автобуса
        QuickSortStrategy strategy = new QuickSortByModelStrategy();
        List<Bus> buses = createTestBuses();
        strategy.sort(buses);

        // Проверка, что первый элемент с "Mercedes", а дальше "Volvo"
        assertEquals("Mercedes", buses.get(0).getModel());
        assertEquals("Volvo", buses.get(1).getModel());
        assertEquals("Volvo", buses.get(2).getModel());
    }

    @Test
    void testQuickSortBySerialNumber() {
        // Сортировку по серийному номеру
        QuickSortStrategy strategy = new QuickSortByNumberStrategy();
        List<Bus> buses = createTestBuses();
        strategy.sort(buses);

        // Проверка последовательности по SerialNumber
        assertEquals(1, buses.get(0).getSerialNumber());
        assertEquals(1, buses.get(1).getSerialNumber()); // два автобуса с разными моделями, но один номер
        assertEquals(2, buses.get(2).getSerialNumber());
    }

    @Test
    void testQuickSortByMileage() {
        // Сортировку по пробегу
        QuickSortStrategy strategy = new QuickSortByMileAgeStrategy();
        List<Bus> buses = createTestBuses();
        strategy.sort(buses);

        // Проверка, что автобусы отсортированы по пробегу (меньший пробег сначала)
        assertEquals(2000, buses.get(0).getMileage());
        assertEquals(3000, buses.get(1).getMileage());
        assertEquals(5000, buses.get(2).getMileage());
    }

    @Test
    void testSortEmptyList() {
        // Сортировка пустого списка, не должно быть ошибок
        QuickSortStrategy strategy = new QuickSortByModelStrategy();
        List<Bus> empty = new ArrayList<>();
        assertDoesNotThrow(() -> strategy.sort(empty));
        assertTrue(empty.isEmpty());
    }

    @Test
    void testSortSingleElement() {
        // Сортировка списка с одним элементом — список не должен измениться и ошибок не должно быть
        QuickSortStrategy strategy = new QuickSortByNumberStrategy();
        List<Bus> single = new ArrayList<>();
        Bus bus = new Bus.BusBuilder().setModel("Test").setSerialNumber(5).setMileage(100).build();
        single.add(bus);

        strategy.sort(single);
        assertEquals(1, single.size());
        assertEquals(bus, single.get(0));
    }

    @Test
    void testSortNullList() {
        // Если список null, метод sort должен просто вернуться, не выбрасывая исключений
        QuickSortStrategy strategy = new QuickSortByMileAgeStrategy();
        assertDoesNotThrow(() -> strategy.sort(null));
    }
}