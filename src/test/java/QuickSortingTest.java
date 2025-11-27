import base.Bus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sorting.BusQuickSorter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuickSortingTest {
    private BusQuickSorter sorter;
    private List<Bus> buses;

    @BeforeEach
    void setUp() {
        sorter = new BusQuickSorter();
        buses = new ArrayList<>();
    }

    //Тест пустого списка
    @Test
    void shouldSortEmptyList() {
        List<Bus> emptyList = new ArrayList<>();
        sorter.sort(emptyList);
        Assertions.assertTrue(emptyList.isEmpty());
    }

    //Тест на один элемент
    @Test
    void shouldSortSingleElement() {
        Bus bus = new Bus.BusBuilder()
                .setModel("Volvo")
                .setSerialNumber(123)
                .setMileage(10000)
                .build();
        buses.add(bus);

        sorter.sort(buses);

        assertEquals(1, buses.size());
        assertEquals(bus, buses.get(0));
    }

    //Тест на null
    @Test
    void shouldHandleNullList() {
        assertDoesNotThrow(() -> sorter.sort(null));
    }

    @Test
    void shouldSortByQuickSorter() {
        // Given - проверка всех трёх полей
        buses.add(new Bus.BusBuilder().setModel("Volvo").setSerialNumber(100).setMileage(5000).build());
        buses.add(new Bus.BusBuilder().setModel("Volvo").setSerialNumber(100).setMileage(3000).build());
        buses.add(new Bus.BusBuilder().setModel("Volvo").setSerialNumber(200).setMileage(1000).build());
        buses.add(new Bus.BusBuilder().setModel("Audi").setSerialNumber(50).setMileage(8000).build());

        // When
        sorter.sort(buses);

        // Then
        assertEquals("Audi", buses.get(0).getModel());
        assertEquals(50, buses.get(0).getSerialNumber());

        assertEquals("Volvo", buses.get(1).getModel());
        assertEquals(100, buses.get(1).getSerialNumber());
        assertEquals(3000, buses.get(1).getMileage());

        assertEquals("Volvo", buses.get(2).getModel());
        assertEquals(100, buses.get(2).getSerialNumber());
        assertEquals(5000, buses.get(2).getMileage());

        assertEquals("Volvo", buses.get(3).getModel());
        assertEquals(200, buses.get(3).getSerialNumber());
    }
}
