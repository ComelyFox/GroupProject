import base.Bus;
import base.SortType;
import org.junit.jupiter.api.Test;
import service.MyArrayList;
import strategy.SortingContext;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ClientSimulationTest {

    //Проверка потока клиента с TrickySort
    @Test
    void testClientFlowTrickySort() {
        List<HashMap<String, Object>> rawData = new ArrayList<>();
        rawData.add(createBusMap(1234, "B832", 6124));
        rawData.add(createBusMap(5678, "Bt99", 94323));
        rawData.add(createBusMap(1111, "Volvo", 5000));

        MyArrayList<Bus> buses = new MyArrayList<>();
        for (HashMap<String, Object> map : rawData) {
            buses.add(new Bus.BusBuilder()
                    .setSerialNumber((int) map.get("number"))
                    .setModel((String) map.get("model"))
                    .setMileage((int) map.get("mileage"))
                    .build());
        }

        SortingContext context = new SortingContext();
        context.setStrategy(SortType.TRICKY_SORT);
        context.executeSort(buses);

        // Проверка, что все элементы на месте
        assertEquals(3, buses.size());
    }

    //Имитация невалидных данных (число mileage неверное)
    @Test
    void testInvalidMileageData() {
        List<HashMap<String, Object>> rawData = new ArrayList<>();
        HashMap<String, Object> invalidBus = new HashMap<>();
        invalidBus.put("number", 1234);
        invalidBus.put("model", "B832");
        invalidBus.put("mileage", "notANumber"); // не число
        rawData.add(invalidBus);

        // Попытка добавить в список Bus должна игнорировать невалидное поле
        MyArrayList<Bus> buses = new MyArrayList<>();
        for (HashMap<String, Object> map : rawData) {
            Object mileageObj = map.get("mileage");
            if (mileageObj instanceof Integer) { // пропускаем, если не число
                buses.add(new Bus.BusBuilder()
                        .setSerialNumber((int) map.get("number"))
                        .setModel((String) map.get("model"))
                        .setMileage((int) map.get("mileage"))
                        .build());
            }
        }

        assertTrue(buses.isEmpty(), "Невалидные данные не должны добавляться");
    }

    //Вспомогательный метод для создания карты автобуса
    private HashMap<String, Object> createBusMap(int number, String model, int mileage) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("number", number);
        map.put("model", model);
        map.put("mileage", mileage);
        return map;
    }
}