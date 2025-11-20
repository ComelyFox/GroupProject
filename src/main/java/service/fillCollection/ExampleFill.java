package service.fillCollection;

import service.fillCollection.fillStrategy.file.FillCollectionFromFile;
import service.fillCollection.fillStrategy.manual.FillCollectionManual;
import service.fillCollection.fillStrategy.random.FillCollectionRandom;

public class ExampleFill {
    public static void main(String[] args) {
        /*Все варианты заполнения возвращают List<HashMap<String, Object>>*/

        FillCollection list = new FillCollectionFromFile("src/main/resources/data.txt");
        list.fillCollection().forEach(System.out::println);

        list = new FillCollectionManual("GH325FK", "G32", 8543);
        list.fillCollection().forEach(System.out::println);

        list = new FillCollectionRandom(2);
        list.fillCollection().forEach(System.out::println);
    }
}
