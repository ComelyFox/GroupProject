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
    private final List<HashMap<String, Object>> list;
    private final String filePath;

    public FillFromFileStrategy(String filePath) {
        this.filePath = filePath;
        this.list = new ArrayList<>();
    }

    private List<HashMap<String, Object>> readFileToCollection() {
        if (!Files.exists(Paths.get(filePath))) {
            System.out.println("Файл не найден");
            return null;
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
                    System.out.println("Строка: " + lineCount + " 3-е поле не является числом");
                    continue;
                }
                HashMap<String, Object> map = new HashMap<>();
                map.put("model", model);
                map.put("number", numberInt);
                map.put("mileage", mileageInt);

                list.add(map);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return list;
    }

    @Override
    public List<HashMap<String, Object>> getFillCollection() {
        return readFileToCollection();
    }
}
