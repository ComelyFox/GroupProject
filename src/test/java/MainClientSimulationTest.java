import base.Bus;
import base.SortType;
import org.junit.jupiter.api.Test;
import service.MyArrayList;
import service.fillCollection.fillStrategy.file.FillFromFileStrategy;
import strategy.SortingContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainClientSimulationTest {

    @Test
    void testClientFlowQuickSort() throws IOException {
        // Создание временного файла с данными автобусов
        Path temp = Files.createTempFile("buses_test", ".txt");
        Files.writeString(temp,
                """
                        HG342KH777, B832, 6124
                        JD909LK44, Bt99, 94323
                        AA111BB22, Volvo, 5000
                        """);

        // Использование стратегии загрузки из файла
        FillFromFileStrategy fillStrategy = new FillFromFileStrategy(temp.toString());
        List<HashMap<String, Object>> rawData = fillStrategy.getFillCollection();
        assertNotNull(rawData);
        assertEquals(3, rawData.size());

        // Преобразование в MyArrayList<Bus>
        MyArrayList<Bus> buses = new MyArrayList<>();
        for (HashMap<String, Object> map : rawData) {
            buses.add(new Bus.BusBuilder()
                    .setSerialNumber((int) map.get("number")) // безопаснее
                    .setModel((String) map.get("model"))
                    .setMileage((int) map.get("mileage"))
                    .build());
        }

        // Применение QuickSort через SortingContext
        SortingContext context = new SortingContext();
        context.setStrategy(SortType.QUICK_SORT);
        context.executeSort(buses);

        // Проверка сортировки по SerialNumber (как пример)
        assertEquals(3, buses.size());
        assertTrue(buses.get(0).getSerialNumber() <= buses.get(1).getSerialNumber());
        assertTrue(buses.get(1).getSerialNumber() <= buses.get(2).getSerialNumber());
    }

    @Test
    void testClientFlowTrickySort() throws IOException {
        // Создание временного файл с данными автобусов
        Path temp = Files.createTempFile("buses_tricky", ".txt");
        Files.writeString(temp,
                """
                        HG342KH777, B832, 6124
                        JD909LK44, Bt99, 94323
                        AA111BB22, Volvo, 5000
                        """);

        FillFromFileStrategy fillStrategy = new FillFromFileStrategy(temp.toString());
        List<HashMap<String, Object>> rawData = fillStrategy.getFillCollection();

        MyArrayList<Bus> buses = new MyArrayList<>();
        for (HashMap<String, Object> map : rawData) {
            buses.add(new Bus.BusBuilder()
                    .setSerialNumber((int) map.get("number")) // безопаснее
                    .setModel((String) map.get("model"))
                    .setMileage((int) map.get("mileage"))
                    .build());
        }

        // Применение TrickySort
        SortingContext context = new SortingContext();
        context.setStrategy(SortType.TRICKY_SORT);
        context.executeSort(buses);

        // Проверка, что элементы не потеряны
        assertEquals(3, buses.size());
    }
}