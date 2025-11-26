package app;

import base.Bus;
import base.CollectionFillType;
import base.SortType;
import service.BusFileWriter;
import service.DataContext;
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
                    dataContext.setStrategy(CollectionFillType.RANDOM, count);
                    buses = dataContext.executeStrategy();
                    return;
                }
                case 3 -> {
                    int count = ui.requestBusCount();
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

        switch (choice) {
            case 1 -> writer.appendBuses(buses);
            case 2 -> {
                String model = ui.requestModel();
                String mileage = ui.requestMileage();
                String serialNumber = ui.requestSerialNumber();

                Bus bus = new DataParser().parseBusData(model, serialNumber, mileage);
                writer.appendBus(bus);
            }
            default -> ui.showError("Неверный выбор!");
        }
    }
}
