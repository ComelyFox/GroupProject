package app;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isExit = false;
        String choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Приложение для сортировки автобусов ===");

        while (!isExit) {
            showMeinMenu();
            try {
                System.out.print("Выш выбор: ");
                choice = scanner.next();
                switch (choice) {
                    case "1" -> showData();
                    case "2" -> fillDataList();
                    case "3" -> sort();
                    case "4" -> isExit = true;
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
                3 - Отсортировать данные
                4 - Выйти из программы
                """);
    }

    public static void showData(){
        //Временно
        System.out.println("Вывели массив\n");
    }

    public static void fillDataList(){
        boolean isExit = false;
        String choice;
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            try {
                System.out.print("""
               Выберите способ заполнения массива:
               1 - Из файла
               2 - Рандом
               3 - Вручную
               4 - Назад
               \s
               Выш выбор:\s""");

                choice = scanner.next();
                switch (choice) {
                    // Выводим в консоль действие (временно)
                    case "1" -> {
                        System.out.println("Заполнили из файла\n");
                        isExit = true;
                    }
                    case "2" -> {
                        System.out.println("Заполнили рандомно\n");
                        isExit = true;
                    }
                    case "3" -> {
                        System.out.println("Заполнили вручную\n");
                        isExit = true;
                    }
                    case "4" -> isExit = true;
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
