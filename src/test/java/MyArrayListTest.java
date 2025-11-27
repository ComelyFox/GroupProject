import base.Bus;
import org.junit.jupiter.api.Test;
import service.MyArrayList;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest {

    @Test
    void testAddAndGetSize() {
        // Тест на добавление элементов и размера
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");
        assertEquals(2, list.size());
    }

    @Test
    void testRemoveByIndexAndObject() {
        // Тест на удаление по индексу и объекту
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");
        String removed = list.remove(0);
        assertEquals("A", removed);
        assertTrue(list.remove("B"));
        assertEquals(0, list.size());
    }

    @Test
    void testContainsAndIndex() {
        // Тест методов contains, indexOf, lastIndexOf
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");
        list.add("A");

        assertTrue(list.contains("A"));
        assertEquals(0, list.indexOf("A"));
        assertEquals(2, list.lastIndexOf("A"));
    }

    @Test
    void testToArrayAndClear() {
        // Тест преобразования в массив и очистку
        MyArrayList<String> list = new MyArrayList<>();
        list.add("X");
        list.add("Y");
        Object[] arr = list.toArray();
        assertArrayEquals(new Object[]{"X", "Y"}, arr);

        list.clear();
        assertEquals(0, list.size ());
    }

    @Test
    void testSubList() {
        // Тест на создание подсписка
        MyArrayList<Integer> list = new MyArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3, 4));
        List<Integer> sub = list.subList(1, 3);
        assertEquals(Arrays.asList(2, 3), sub);
    }

    @Test
    void testIterator() {
        // Тест итератора и remove
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");

        var iter = list.iterator();
        assertTrue(iter.hasNext());
        assertEquals("A", iter.next());
        iter.remove(); // удаляем "A"
        assertEquals(1, list.size());
    }

    @Test
    void testConcurrentMethod(){
        MyArrayList<Bus> list = new MyArrayList<>();
        var bus1 = new Bus.BusBuilder()
                .setModel("Сарай")
                .setSerialNumber(777666)
                .setMileage(1000000)
                .build();
        var bus2 = new Bus.BusBuilder()
                .setModel("Сарай NEO")
                .setSerialNumber(777666)
                .setMileage(1000000)
                .build();
        for (int i = 0; i < 5; i++) {
            list.add(bus1);
        }
        for (int i = 0; i < 2; i++) {
            list.add(bus2);
        }

        var busResult = new Bus.BusBuilder()
                .setModel("Сарай")
                .setSerialNumber(777666)
                .setMileage(1000000)
                .build();

        int res1 = list.countOccurrences(busResult, 3);
        int res2 = list.countParallelOccurrences(busResult);

        assertEquals(5, res1);
        assertEquals(5, res2);
    }
}