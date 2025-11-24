import org.junit.Test;
import service.fillCollection.Fill;

public class FillTest {
    @Test
    public void getBusCollectionFromFileTest() {
        Fill.FillBus.getBusCollectionFromFile("src/main/resources/data.txt")
                .forEach(bus -> System.out.println("Bus: " + bus.getModel() + ", " + bus.getSerialNumber() + ", " + bus.getMileage()));
    }

    @Test
    public void getBusCollectionManualTest() {
        Fill.FillBus.getBusCollectionManual("Volvo", 820, 34)
                .forEach(bus -> System.out.println("Bus: " + bus.getModel() + ", " + bus.getSerialNumber() + ", " + bus.getMileage()));
    }

    @Test
    public void getBusCollectionRandomTest() {
        Fill.FillBus.getBusCollectionRandom(3)
                .forEach(bus -> System.out.println("Bus: " + bus.getModel() + ", " + bus.getSerialNumber() + ", " + bus.getMileage()));
    }

}
