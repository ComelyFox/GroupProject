package app;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isExit = false;
        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Приложение для сортировки автобусов ===");

        while (!isExit) {
            showMeinMenu();
            try {
                System.out.print("Выш выбор: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> showData();
                    case 2 -> fillDataList();
                    case 3 -> changeSort(); // по умолчанию сортируем по первому полю
                    case 4 -> sort();
                    case 5 -> isExit = true;
                    default -> System.out.println("Неверный ввод!\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод: " + e.getMessage());
            }
        }
    }

    public static void showMeinMenu(){
        System.out.println("""
                Выберите действие:
                1 - Показать массив данных
                2 - Заполнить массив данных
                3 - Изменить поле сортировки
                4 - Отсортировать данные
                5 - Выйти из программы
                """);
    }

    public static void showData(){
        //Временно
        System.out.println("Вывели массив\n");
    }

    public static void fillDataList(){
        System.out.println("""
                Выберите способ заполнения массива:
                1 - Из файла
                2 - Рандом
                3 - Вручную
                4 - Назад""");

        boolean isExit = false;
        int choice;
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            try {
                choice = scanner.nextInt();
                System.out.print("Выш выбор: ");
                switch (choice) {
                    // Выводим в консоль действие (временно)
                    case 1 -> System.out.println("Заполнили из файла");
                    case 2 -> System.out.println("Заполнили рандомно");
                    case 3 -> System.out.println("Заполнили вручную");
                    case 4 -> isExit = true;
                    default -> System.out.println("Неверный ввод!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод: " + e.getMessage());
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
        Scanner scanner = new Scanner(System.in);

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
        // Временно
        System.out.println("Сортируем...\n");
    }
}
