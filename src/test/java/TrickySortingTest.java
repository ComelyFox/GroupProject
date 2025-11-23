import base.Bus;
import base.SortType;
import org.junit.jupiter.api.Test;
import service.MyArrayList;
import strategy.SortingContext;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class TrickySortingTest {

    @Test
    void shouldTrickySorting(){
        Bus bus1 = new Bus.BusBuilder()
            .setModel("ГАЗ")
            .setSerialNumber(4)
            .setMileage(123)
            .build();
        Bus bus2 = new Bus.BusBuilder()
                .setModel("ПАЗ")
                .setSerialNumber(3)
                .setMileage(2445)
                .build();
        Bus bus3 = new Bus.BusBuilder()
                .setModel("МАЗ")
                .setSerialNumber(2)
                .setMileage(3444)
                .build();
        Bus bus4 = new Bus.BusBuilder()
                .setModel("ЛиАЗ")
                .setSerialNumber(1)
                .setMileage(100)
                .build();

        MyArrayList<Bus> busesList = new MyArrayList<>();
        busesList.addAll(Arrays.asList(bus1, bus2, bus3, bus4));
        SortingContext context = new SortingContext();


        context.setStrategy(SortType.TRICKY_SORT);

        context.executeSort(busesList);

        // Assert
        assertEquals(4, busesList.size());

        Bus expected1 = new Bus.BusBuilder()
                .setModel("МАЗ")
                .setSerialNumber(2)
                .setMileage(3444)
                .build();
        assertEquals(expected1, busesList.get(0));

        Bus expected2 = new Bus.BusBuilder()
                .setModel("ПАЗ")
                .setSerialNumber(3)
                .setMileage(2445)
                .build();
        assertEquals(expected2, busesList.get(1));

        Bus expected3 = new Bus.BusBuilder()
                .setModel("ГАЗ")
                .setSerialNumber(4)
                .setMileage(123)
                .build();
        assertEquals(expected3, busesList.get(2));

        Bus expected4 = new Bus.BusBuilder()
                .setModel("ЛиАЗ")
                .setSerialNumber(1)
                .setMileage(100)
                .build();
        assertEquals(expected4, busesList.get(3));
    }
}
