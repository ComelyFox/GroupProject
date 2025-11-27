package app;

import java.util.Scanner;

/**
 * Класс пользовательского интерфейса. Только базовые запросы данных. Ничего не знает о стратегиях.
 */

public class UserInterface {
    private final Scanner scanner;
    private boolean isGreeting = false;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    // Все методы меню
    public int showMainMenu() {
        if (!isGreeting) {
            System.out.println("=== Добро пожаловать в приложение для сортировки автобусов ===");
            isGreeting = true;
        }
        System.out.println("""
            Выберите действие:
            1 - Показать массив данных
            2 - Заполнить массив данных
            3 - Отсортировать данные
            4 - Сохранить в файл
            5 - Поиск в коллекции (многопоточный метод)
            6 - Выйти из программы
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
            2 - Продвинутая сортировка
            3 - Отмена""");
        return readInt("Ваш выбор: ");
    }

    public int showSaveMenu() {
        System.out.println("""
            Выберите тип записи:
            1 - Сохранить коллекцию (режим добавления данных)
            2 - Перезаписать коллекцию (старые данные удаляются)
            3 - Добавить автобус
            4 - Отмена""");
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
        System.out.print("Введите количество автобусов (0 для отмены): ");
        try {
            int response = Integer.parseInt(scanner.nextLine().trim());
            if (response < 0 || response > 100) {throw new NumberFormatException();}
            return response;
        } catch (NumberFormatException e) {
            showError("Неверный формат числа. Допустимый диапазон: 0-100");
            return requestBusCount();
        }
    }

    public int requestThreadCount() {
        System.out.print("Введите количество потоков (0 для отмены): ");
        try {
            int response = Integer.parseInt(scanner.nextLine().trim());
            if (response < 0 || response > 4) {throw new NumberFormatException();}
            return response;
        } catch (NumberFormatException e) {
            showError("Неверный формат числа. Допустимый диапазон: 0-4");
            return requestBusCount();
        }
    }

    public String requestModel() {
        System.out.print("Введите модель автобуса (0 для отмены): ");
        return scanner.nextLine().trim();
    }

    public String requestSerialNumber() {
        System.out.print("Введите серийный номер (0 для отмены): ");
        return scanner.nextLine().trim();
    }

    public String requestMileage() {
        System.out.print("Введите пробег (0 для отмены): ");
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
