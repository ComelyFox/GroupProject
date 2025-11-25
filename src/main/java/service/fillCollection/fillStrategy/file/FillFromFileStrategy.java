package service.fillCollection.fillStrategy.file;

import service.fillCollection.fillStrategy.IFillCollection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FillFromFileStrategy implements IFillCollection {
    private final HashMap<String, Object> mapEntityBus;
    private final List<HashMap<String, Object>> listMapEntityBus;
    private String filePath;

    public FillFromFileStrategy() {
        this.mapEntityBus = new HashMap<>();
        this.listMapEntityBus = new ArrayList<>();
        this.filePath = "";
    }

    public void setPath(String path) {
        this.filePath = path;
    }

    @Override
    public List<HashMap<String, Object>> fillCollection() {
        if (!Files.exists(Paths.get(filePath)) || filePath.isBlank()) {
            System.out.println("Файл не найден");
            return listMapEntityBus;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            int lineCount = 0;
            int mileageInt;
            int numberInt;

            while ((line = reader.readLine()) != null) {
                lineCount++;
                String[] parts = line.split(",");

                if (parts.length != 3) {
                    System.out.println("Cтрока: " + lineCount + " количество полей != 3");
                    continue;
                }

                String model = parts[0].trim();
                String number = parts[1].trim();
                String mileage = parts[2].trim();

                try {
                    numberInt = Integer.parseInt(number);
                    mileageInt = Integer.parseInt(mileage);

                    if (model.chars().anyMatch(Character::isDigit)) {
                        System.out.println("Строка: " + lineCount + " , модель содержит недопустимое значение " + model);
                    }

                    if (numberInt < 0 || mileageInt < 0) {
                        System.out.println("Строка: " + lineCount + " содержит отрицательное число");
                        continue;
                    }
                }  catch (NumberFormatException e) {
                    System.out.println("Строка: " + lineCount + " поле не является числом");
                    continue;
                }
                mapEntityBus.clear();
                mapEntityBus.put("model", model);
                mapEntityBus.put("number", numberInt);
                mapEntityBus.put("mileage", mileageInt);
                listMapEntityBus.add(new HashMap<>(mapEntityBus));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listMapEntityBus;
    }

    @Override
    public void clearStrategyCollection() {
        listMapEntityBus.clear();
    }
}
