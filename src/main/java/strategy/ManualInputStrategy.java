package strategy;

import app.UserInterface;
import base.Bus;
import service.DataParser;
import service.MyArrayList;


/**
 * Класс для ручного ввода. Взаимодействует с UserInterface
 */
public class ManualInputStrategy implements DataInputStrategy {
    private final DataParser dataParser = new DataParser();
    private final UserInterface userInterface = new UserInterface();
    private int size;

    public ManualInputStrategy(int size) {
        this.size = size;
    }

    @Override
    public MyArrayList<Bus> getBuses() {
        MyArrayList<Bus> buses = new MyArrayList<>();
        for (int i = 0; i < size; i++) {
            // Интерактивный диалог с пользователем
            String model = userInterface.requestModel();
            if ("0".equals(model)) {
                userInterface.showInfo("Операция отменена");
                return new MyArrayList<>();
            }

            String serialNumber = userInterface.requestSerialNumber();
            if ("0".equals(serialNumber)) {
                userInterface.showInfo("Операция отменена");
                return new MyArrayList<>();
            }

            String mileage = userInterface.requestMileage();
            if ("0".equals(mileage)) {
                userInterface.showInfo("Операция отменена");
                return new MyArrayList<>();
            }

            Bus bus = dataParser.parseBusData(model, serialNumber, mileage);
            if (bus != null) {
                buses.add(bus);
            } else {
                userInterface.showError("Неверные данные, попробуйте снова");
                i--; // повторяем ввод
            }
        }

        return buses;
    }
}