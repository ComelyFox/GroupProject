package app;

import base.Bus;
import base.CollectionFillType;
import base.SortType;
import service.BusFileWriter;
import service.DataContext;
import service.DataParser;
import service.MyArrayList;
import strategy.SortingContext;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Bus> buses = new MyArrayList<>();
    private static final UserInterface userInterface = new UserInterface();

    public static void main(String[] args) {
        boolean isExit = false;
        int choice;

        System.out.println("=== Приложение для сортировки автобусов ===");

        while (!isExit) {
            showMainMenu();
            try {
                System.out.print("Выш выбор: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> showData();
                    case 2 -> fillDataList();
                    case 3 -> changeSort(); // по умолчанию сортируем по первому полю
                    case 4 -> sort();
                    case 5 -> addToFile();  //добавление коллекции в файл
                    case 6 -> isExit = true;
                    default -> System.out.println("Неверный ввод!\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод: " + e.getMessage());
            }
        }
    }

    public static void showMainMenu(){
        System.out.println("""
                Выберите действие:
                1 - Показать массив данных
                2 - Заполнить массив данных
                3 - Изменить поле сортировки
                4 - Отсортировать данные
                5 - Сохранить в файл
                6 - Выйти из программы
                """);
    }

    public static void showData(){
        //Временно
        System.out.println(buses);
    }

    public static void fillDataList() {
        DataContext context = new DataContext();

        while (true) {
            System.out.println("""
                Выберите способ заполнения массива:
                1 - Из файла
                2 - Рандом
                3 - Вручную
                4 - Назад""");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        context.setStrategy(CollectionFillType.FROM_FILE, 0);
                        break;
                    case 2:
                        int count2 = userInterface.requestBusCount();
                        context.setStrategy(CollectionFillType.RANDOM, count2);
                        break;
                    case 3:
                        int count3 = userInterface.requestBusCount();
                        context.setStrategy(CollectionFillType.MANUAL, count3);
                        break;
                    case 4:
                        return; // выходим из метода
                    default:
                        System.out.println("Неверный выбор!");
                }
                buses = context.executeStrategy();
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод! Введите число.");
            }
        }
    }

    public static void changeSort(){
        System.out.println("""
                Выберите поле для сортировки:
                1 - Первое поле
                2 - Второе поле
                3 - Третье поле
                4 - Назад""");

        boolean isExit = false;
        int choice;

        while (!isExit) {
            try {
                System.out.print("Выш выбор: ");
                choice = scanner.nextInt();
                switch (choice) {
                    // Выводим в консоль действие (временно)
                    case 1 -> System.out.println("Сортировка будет по первому полю");
                    case 2 -> System.out.println("Сортировка будет по второму полю");
                    case 3 -> System.out.println("Сортировка будет по третьему полю");
                    case 4 -> isExit = true;
                    default -> System.out.println("Неверный ввод!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод: " + e.getMessage());
            }
        }
    }

    public static void sort(){
        SortingContext context = new SortingContext();

        System.out.println("""
                Выберите тип сортировки:
                1 - Быстрая сортировка
                2 - Продвинутая сортировка
                """);

        int choice = scanner.nextInt();
        SortType selectedType = switch (choice) {
            case 1 -> SortType.QUICK_SORT;
            case 2 -> SortType.TRICKY_SORT;
            default -> throw new IllegalArgumentException("Неверный выбор");
        };

        context.setStrategy(selectedType);
        context.executeSort(buses);

        System.out.println("Результат сортировки:");
        buses.forEach(System.out::println);
    }


    private static void addToFile() {
        DataParser dataParser = new DataParser();
        BusFileWriter writer = new BusFileWriter("buses.txt");

        System.out.println("""
                Выберите тип записи:
                1 - Сохранить коллекцию
                2 - Добавить автобус
                """);

        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> writer.appendBuses(buses);
            case 2 -> {
                String model = userInterface.requestModel();
                String mileage = userInterface.requestMileage();
                String serialNumber = userInterface.requestSerialNumber();

                Bus bus = dataParser.parseBusData(model, serialNumber, mileage);
                writer.appendBus(bus);
            }
            default -> throw new IllegalArgumentException("Неверный выбор");
        }
    }
}
