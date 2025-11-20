package service.fillCollection;

import service.fillCollection.fillStrategy.IFillCollection;
import service.fillCollection.fillStrategy.file.FillCollectionFromFile;
import service.fillCollection.fillStrategy.manual.FillCollectionManual;
import service.fillCollection.fillStrategy.random.FillCollectionRandom;

import java.util.HashMap;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<HashMap<String, Object>> list;
//
//        FillManual fm = new FillManual();
//        fm.getCollection("J452OD77", "GP4", 6452);
//        list = fm.getCollection("H345HG77", "GP3", 783);
//        list.forEach(System.out::println);
////
//        list = FillFromFile.read("src/main/resources/data.txt");
//        list.forEach(System.out::println);

//        List<Object> list1 = new ArrayList<>(FillRandom.getRandomBus());
//        list1.forEach(System.out::println);

//        randomFill = new FillRandomStrategy(5);
//        list = randomFill.getFillCollection();
//        list.forEach(System.out::println);

//      random
        FillCollection fc = new FillCollectionRandom(3);
        list = fc.fillCollection();
        list.forEach(System.out::println);
//      file
        fc = new FillCollectionFromFile("src/main/resources/datha.txt");
        list = fc.fillCollection();
        list.forEach(System.out::println);
//      manual
        fc = new FillCollectionManual("GH325FK", "G32", 8543);
        list = fc.fillCollection();
        list.forEach(System.out::println);




    }
}
