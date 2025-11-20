package service.fillCollection;

import service.fillCollection.fillStrategy.IFillCollection;
import service.fillCollection.fillStrategy.file.FillCollectionFromFile;
import service.fillCollection.fillStrategy.manual.FillCollectionManual;
import service.fillCollection.fillStrategy.random.FillCollectionRandom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {

        /*
        Все варианты заполнения возвращают List<HashMap<String, Object>>
        */
//      file
        List<HashMap<String, Object>> listCollectionFromFile = new FillCollectionFromFile("src/main/resources/data.txt").fillCollection();
//      manual
        List<HashMap<String, Object>> listCollectionManual = new FillCollectionManual("GH325FK", "G32", 8543).fillCollection();
//      random
        List<HashMap<String, Object>> listCollectionRandom = new FillCollectionRandom(10).fillCollection();

        Stream.concat(
                Stream.concat(listCollectionManual.stream(), listCollectionFromFile.stream()),
                listCollectionRandom.stream()
                ).forEach(System.out::println);







    }
}
