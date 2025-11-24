package service.fillCollection;

import base.Bus;
import service.fillCollection.fillStrategy.file.FillCollectionFromFile;
import service.fillCollection.fillStrategy.manual.FillCollectionManual;
import service.fillCollection.fillStrategy.random.FillCollectionRandom;

import java.util.HashMap;
import java.util.List;

public class Fill {
    public static class FillBus {
        private static List<Bus> getBus(List<HashMap<String, Object>> listBus) {
            return listBus.stream()
                    .map(hashMap -> new Bus.BusBuilder()
                            .setSerialNumber((Integer) hashMap.get("number"))
                            .setModel((String) hashMap.get("model"))
                            .setMileage((Integer) hashMap.get("mileage"))
                            .build())
                    .toList();
        }

        public static List<Bus> getBusCollectionFromFile(String path) {
            return getBus(new FillCollectionFromFile(path).fillCollection());
        }

        public static List<Bus> getBusCollectionManual(String model, int number, int mileage) {
            return getBus(new FillCollectionManual(model, number, mileage).fillCollection());
        }

        public static List<Bus> getBusCollectionRandom(int length) {
            return getBus(new FillCollectionRandom(length).fillCollection());
        }
    }
}
