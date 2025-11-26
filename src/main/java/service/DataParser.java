package service;

import base.Bus;

/**
 * Класс для парсинга введенных данных. Используется всеми стратегиями т.к. и в ручном вводе и из файла мы вычитываем
 * строки. Здесь они парсятся и возвращается готовый автобус или null если что-то не так.
 */
public class DataParser {
    public Bus parseBusData(String model, String serialNumberStr, String mileageStr) {
        try {
            int serialNumber = Integer.parseInt(serialNumberStr);
            int mileage = Integer.parseInt(mileageStr);

            return new Bus.BusBuilder()
                    .setModel(model)
                    .setSerialNumber(serialNumber)
                    .setMileage(mileage)
                    .build();

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка парсинга");
            return null;
        }
    }
}
