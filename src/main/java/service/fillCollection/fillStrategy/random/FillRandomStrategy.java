package service.fillCollection.fillStrategy.random;

import service.fillCollection.fillStrategy.IFillCollection;
import service.fillCollection.fillStrategy.random.parts.FillRandomCharPart;
import service.fillCollection.fillStrategy.random.parts.FillRandomIntPart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class FillRandomStrategy implements IFillCollection {
    int len;
    public FillRandomStrategy(int len){
        this.len = len;
    }

    private static String getRandomNumber() {
        return String.valueOf(new FillRandomCharPart(1).random()
                .append(new FillRandomIntPart(3).random())
                .append(new FillRandomCharPart(2).random())
                .append(new FillRandomIntPart(2).random()));
    }

    private static String getRandomModel() {
        return String.valueOf(new FillRandomCharPart(2).random()
                .append(new FillRandomIntPart(1).random()));
    }

    private static int getRandomMileage() {
        Random rand = new Random();
        return rand.nextInt(1000000);
    }

    private List<HashMap<String, Object>> getRandomBus() {
        List<HashMap<String, Object>> bus = new ArrayList<>();
        for (int i = 0; i < this.len; i++){
            HashMap<String, Object> entity = new HashMap<>();
            entity.put("number", getRandomNumber());
            entity.put("model", getRandomModel());
            entity.put("mileage", getRandomMileage());
            bus.add(entity);
        }
        return bus;
    }

    @Override
    public List<HashMap<String, Object>> getFillCollection() {
        return getRandomBus();
    }
}
