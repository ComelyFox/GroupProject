package service;

import base.Bus;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Чтение из файла построчно в формате модель,номер,пробег.
 */

public class FileInputStrategy implements DataInputStrategy {
    private final String filePath;
    private final DataParser dataParser = new DataParser();

    public FileInputStrategy() {
        this.filePath = "buses.txt";
    }

    @Override
    public List<Bus> getBuses() {
        int addedBusCount = 0;
        int skippedBusCount = 0;
        List<Bus> validBuses = new MyArrayList<>();

        try {
            Path path = Paths.get(filePath).toAbsolutePath();

            if (!Files.exists(path)) {
                throw new RuntimeException("Файл не найден в корне проекта: " + path);
            }

            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                Bus bus = parseLine(line);
                if (bus != null) {
                    validBuses.add(bus);
                    addedBusCount++;
                } else {
                    skippedBusCount++;
                }
            }

            // Вывод результатов
            System.out.println(" Загрузка из файла завершена:");
            System.out.println(" Добавлено автобусов: " + addedBusCount);
            System.out.println(" Пропущено невалидных строк: " + skippedBusCount);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка чтения файла: " + e.getMessage(), e);
        }

        return validBuses;
    }

    private Bus parseLine(String line) {
        String[] parts = line.split(",");  // формат: модель,номер,пробег
        if (parts.length == 3) {
            return dataParser.parseBusData(parts[0].trim(), parts[1].trim(), parts[2].trim());
        }
        return null;
    }
}
