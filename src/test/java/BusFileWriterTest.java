package service;

import base.Bus;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BusFileWriterTest {

    private static Path tempFile;
    private BusFileWriter busFileWriter;

    @BeforeAll
    static void setupAll() throws IOException {
        // Создание временного файл для тестов
        tempFile = Files.createTempFile("buses", ".txt");
    }

    @AfterAll
    static void tearDownAll() throws IOException {
        // Удаление временного файл после всех тестов
        Files.deleteIfExists(tempFile);
    }

    @BeforeEach
    void setUp() {
        // Создание нового экземпляра BusFileWriter перед каждым тестом
        busFileWriter = new BusFileWriter(tempFile.toString());
    }

    @AfterEach
    void tearDown() throws IOException {
        // Очищение файла после каждого теста, чтобы тесты не влияли друг на друга
        Files.writeString(tempFile, "", StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Test
    void testAppendSingleBus() throws IOException {
        // Тест проверяет добавление одного автобуса в файл
        Bus bus = new Bus.BusBuilder()
                .setModel("Volvo")
                .setSerialNumber(1234)
                .setMileage(50000)
                .build();

        busFileWriter.appendBus(bus);

        List<String> lines = Files.readAllLines(tempFile);
        assertEquals(1, lines.size());
        assertEquals("Volvo,1234,50000", lines.get(0));
    }

    @Test
    void testAppendMultipleBuses() throws IOException {
        // Тест проверяет добавление нескольких автобусов в файл за один вызов
        Bus bus1 = new Bus.BusBuilder()
                .setModel("Mercedes")
                .setSerialNumber(1111)
                .setMileage(30000)
                .build();
        Bus bus2 = new Bus.BusBuilder()
                .setModel("Scania")
                .setSerialNumber(2222)
                .setMileage(40000)
                .build();

        busFileWriter.appendBuses(List.of(bus1, bus2));

        List<String> lines = Files.readAllLines(tempFile);
        assertEquals(2, lines.size());
        assertEquals("Mercedes,1111,30000", lines.get(0));
        assertEquals("Scania,2222,40000", lines.get(1));
    }

    @Test
    void testAppendNullBus() throws IOException {
        // Тест проверяет поведение при попытке добавить null.
        // Файл должен остаться пустым, а программа не должна падать
        busFileWriter.appendBus(null);

        List<String> lines = Files.readAllLines(tempFile);
        assertTrue(lines.isEmpty());
    }
}