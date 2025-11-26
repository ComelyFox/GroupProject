package app;

import java.util.Scanner;

/**
 * Класс пользовательского интерфейса. Только базовые запросы данных. Ничего не знает о стратегиях.
 */

public class UserInterface {
    private final Scanner scanner;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    // Все методы меню
    public int showMainMenu() {
        System.out.println("=== Приложение для сортировки автобусов ===");
        System.out.println("""
            Выберите действие:
            1 - Показать массив данных
            2 - Заполнить массив данных
            3 - Отсортировать данные
            4 - Сохранить в файл
            5 - Выйти из программы
            """);
        return readInt("Ваш выбор: ");
    }

    public int showFillDataMenu() {
        System.out.println("""
            Выберите способ заполнения массива:
            1 - Из файла
            2 - Рандом
            3 - Вручную
            4 - Назад""");
        return readInt("Ваш выбор: ");
    }

    public int showSortTypeMenu() {
        System.out.println("""
            Выберите тип сортировки:
            1 - Быстрая сортировка
            2 - Продвинутая сортировка""");
        return readInt("Ваш выбор: ");
    }

    public int showSaveMenu() {
        System.out.println("""
            Выберите тип записи:
            1 - Сохранить коллекцию
            2 - Добавить автобус""");
        return readInt("Ваш выбор: ");
    }

    // Вспомогательные методы
    private int readInt(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            showError("Неверный формат числа");
            return readInt(prompt);
        }
    }

    public int requestBusCount() {
        System.out.print("Введите количество автобусов: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            showError("Неверный формат числа");
            return requestBusCount();
        }
    }

    public String requestModel() {
        System.out.print("Введите модель автобуса: ");
        return scanner.nextLine().trim();
    }

    public String requestSerialNumber() {
        System.out.print("Введите серийный номер: ");
        return scanner.nextLine().trim();
    }

    public String requestMileage() {
        System.out.print("Введите пробег: ");
        return scanner.nextLine().trim();
    }

    public void showError(String message) {
        System.out.println("Ошибка: " + message);
    }

    public void showSuccess(String message) {
        System.out.println("✓ " + message);
    }

    public void showInfo(String message) {
        System.out.println(message);
    }
}
