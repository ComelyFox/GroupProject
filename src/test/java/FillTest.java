import service.fillCollection.FillBus;
import service.fillCollection.fillStrategy.file.FillBusFromFile;
import service.fillCollection.fillStrategy.manual.FillBusManual;
import service.fillCollection.fillStrategy.random.FillBusRandom;

public class FillTest {
    public static void main(String[] args) {
        FillBusFromFile file = new FillBusFromFile();
        FillBusManual manual = new FillBusManual();
        FillBusRandom random = new FillBusRandom();

        FillBus busCollection = file;
        busCollection.setPath("src/main/resources/data.txt");
        busCollection.fillCollection();
        busCollection.fillCollection();//заполнит еще раз из файла
        System.out.println(busCollection.getBusList());

        busCollection = manual;
        busCollection.setBus("Volvo", 3,4);
        busCollection.setBus("Scania", 323,907);
        busCollection.setBus("Man", 843,732);
        busCollection.fillCollection();
        busCollection.fillCollection();//пустой вызов
        System.out.println(busCollection.getBusList());

        busCollection = random;
        busCollection.setQuantityBus(2);
        busCollection.fillCollection();
        busCollection.fillCollection();//пустой вызов
        System.out.println(busCollection.getBusList());

        busCollection = manual;
        busCollection.setBus("Kamaz", 6843,57382);
        busCollection.fillCollection();
        System.out.println(busCollection.getBusList());
    }
}
