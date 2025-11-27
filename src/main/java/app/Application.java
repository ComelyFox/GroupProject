package app;

import base.Bus;
import base.CollectionFillType;
import base.SortType;
import service.BusFileWriter;
import strategy.DataContext;
import service.DataParser;
import service.MyArrayList;
import strategy.SortingContext;

import java.util.List;

public class Application {
    private final UserInterface ui;
    private final DataContext dataContext;
    private final SortingContext sortingContext;
    private List<Bus> buses;

    public Application() {
        this.ui = new UserInterface();
        this.dataContext = new DataContext();
        this.sortingContext = new SortingContext();
        this.buses = new MyArrayList<>();
    }

    public void run() {
        boolean isExit = false;

        while (!isExit) {
            int choice = ui.showMainMenu();

            switch (choice) {
                case 1 -> showData();
                case 2 -> fillDataList();
                case 3 -> sort();
                case 4 -> addToFile();
                case 5 -> isExit = true;
                default -> ui.showError("Неверный выбор!");
            }
        }
    }

    private void showData() {
        if (buses.isEmpty()){
            System.out.println("Коллекция пуста");
        } else {
            ui.showInfo("Текущие данные:");
            buses.forEach(bus -> ui.showInfo(bus.toString()));
        }
    }

    private void fillDataList() {
        while (true) {
            int choice = ui.showFillDataMenu();

            switch (choice) {
                case 1 -> {
                    dataContext.setStrategy(CollectionFillType.FROM_FILE, 0);
                    buses = dataContext.executeStrategy();
                    return;
                }
                case 2 -> {
                    int count = ui.requestBusCount();
                    if (count == 0){
                        ui.showInfo("Операция отменена");
                        return;
                    }
                    dataContext.setStrategy(CollectionFillType.RANDOM, count);
                    buses = dataContext.executeStrategy();
                    return;
                }
                case 3 -> {
                    int count = ui.requestBusCount();
                    if (count == 0){
                        ui.showInfo("Операция отменена");
                        return;
                    }
                    dataContext.setStrategy(CollectionFillType.MANUAL, count);
                    buses = dataContext.executeStrategy();
                    return;
                }
                case 4 -> { return; }
                default -> ui.showError("Неверный выбор!");
            }
        }
    }

    private void sort() {
        if (buses.isEmpty()){
            System.out.println("Невозможно отсортировать. Коллекция пуста");
        } else {
            int choice = ui.showSortTypeMenu();

            if (choice == 3){
                return;
            }

            SortType type = switch (choice) {
                case 1 -> SortType.QUICK_SORT;
                case 2 -> SortType.TRICKY_SORT;
                default -> throw new IllegalArgumentException("Неверный выбор");
            };

            sortingContext.setStrategy(type);
            sortingContext.executeSort(buses);
            ui.showSuccess("Данные отсортированы");
        }
    }

    private void addToFile() {
        int choice = ui.showSaveMenu();
        BusFileWriter writer = new BusFileWriter("buses.txt");

        if (buses.isEmpty()){
            ui.showInfo("Список пуст. Нечего сохранять");
            return;
        }

        switch (choice) {
            case 1 -> {
                writer.appendBuses(buses, true);
                ui.showSuccess("Коллекция сохранена в режиме добавления данных");
            }
            case 2 -> {
                writer.appendBuses(buses, false);
                ui.showSuccess("Коллекция перезаписана");
            }
            case 3 -> {
                String model = ui.requestModel();
                if ("0".equals(model)) {
                    ui.showInfo("Операция отменена");
                    return;
                }

                String serialNumber = ui.requestSerialNumber();
                if ("0".equals(serialNumber)) {
                    ui.showInfo("Операция отменена");
                    return;
                }

                String mileage = ui.requestMileage();
                if ("0".equals(mileage)) {
                    ui.showInfo("Операция отменена");
                    return;
                }

                Bus bus = new DataParser().parseBusData(model, serialNumber, mileage);
                writer.appendBus(bus);
                ui.showSuccess("Автобус добавлен в файл");
            }
            case 4 -> {return;}
            default -> ui.showError("Неверный выбор!");
        }
    }
}
