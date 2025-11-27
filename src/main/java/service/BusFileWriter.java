package service;

import app.UserInterface;
import base.Bus;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * BufferedWriter для добавления коллекции автобусов в файл
 */
public class BusFileWriter {
    private final UserInterface userInterface = new UserInterface();
    private final DataParser dataParser = new DataParser();
    private final String filePath;

    public BusFileWriter(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Добавляет коллекцию автобусов в конец файла или перезаписывает с удалением старых данных
     * в зависимости от переданного аргумента append
     */
    public void appendBuses(List<Bus> buses, boolean append) {
        StandardOpenOption[] options = append ?
                new StandardOpenOption[] { StandardOpenOption.APPEND, StandardOpenOption.CREATE,} :
                new StandardOpenOption[] { StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE};

        try (BufferedWriter writer = Files.newBufferedWriter(
                Path.of(filePath), options
        )) {
            for (Bus bus : buses) {
                String busData = bus.getModel() + "," +
                        bus.getSerialNumber() + "," +
                        bus.getMileage();
                writer.write(busData);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл: " + e.getMessage(), e);
        }
    }

    public void appendBus(Bus bus) {

        if (bus != null) {
            try (BufferedWriter writer = Files.newBufferedWriter(
                    Path.of(filePath),
                    StandardOpenOption.APPEND,
                    StandardOpenOption.CREATE
            )) {
                String busData = bus.getModel() + "," + bus.getSerialNumber() + "," + bus.getMileage();
                    writer.write(busData);
                    writer.newLine();
            } catch (IOException e) {
                throw new RuntimeException("Ошибка записи в файл: " + e.getMessage(), e);
            }
        } else {
            userInterface.showError("Неверные данные, попробуйте снова");
        }
    }
}
