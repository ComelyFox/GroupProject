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
    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }
}
