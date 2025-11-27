package strategy;

import base.Bus;
import base.BusModel;
import service.MyArrayList;

import java.util.List;
import java.util.Random;

public class RandomInputStrategy implements DataInputStrategy {
    private final int count;
    private final Random random = new Random();

    public RandomInputStrategy(int count) {
        this.count = count;
    }

    @Override
    public List<Bus> getBuses() {
        List<Bus> buses = new MyArrayList<>();

        for (int i = 0; i < count; i++) {
            BusModel randomModel = BusModel.randomModel();
            int serialNumber = 1000 + random.nextInt(9000); // 1000-9999
            int mileage = random.nextInt(200000); // 0-200,000 км

            Bus bus = new Bus.BusBuilder()
                    .setModel(randomModel.getDisplayName())
                    .setSerialNumber(serialNumber)
                    .setMileage(mileage)
                    .build();

            buses.add(bus);

            System.out.println((i + 1) + " " + bus.toString());
        }

        return buses;
    }
}
