package app;

import java.util.Scanner;

/**
 * Класс пользовательского интерфейса. Только базовые запросы данных. Ничего не знает о стратегиях.
 */

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);

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
