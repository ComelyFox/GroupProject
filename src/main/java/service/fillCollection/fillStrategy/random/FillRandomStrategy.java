package service.fillCollection.fillStrategy.random;

import service.fillCollection.fillStrategy.IFillCollection;
import service.fillCollection.fillStrategy.random.parts.FillRandomCharPart;
import service.fillCollection.fillStrategy.random.parts.FillRandomIntPart;

import java.util.*;

public class FillRandomStrategy implements IFillCollection {
    int len;
    List<String> listModels = List.of("Volvo", "Mercedes", "Scania", "Renault", "Kamaz", "DAF", "Man",
            "Iveco", "Isuzu", "SITRAK", "Shacman", "JAC");

    public FillRandomStrategy(int len){
        this.len = len;
    }

    private String getRandomModel() {
        return listModels.get(new Random().nextInt(listModels.size()));
    }

    private int getRandomInt() {
        Random rand = new Random();
        return rand.nextInt(1000000);
    }

    private List<HashMap<String, Object>> getRandomBus() {
        List<HashMap<String, Object>> bus = new ArrayList<>();
        for (int i = 0; i < this.len; i++){
            HashMap<String, Object> entity = new HashMap<>();
            entity.put("model", getRandomModel());
            entity.put("number", getRandomInt());
            entity.put("mileage", getRandomInt());
            bus.add(entity);
        }
        return bus;
    }

    @Override
    public List<HashMap<String, Object>> getFillCollection() {
        return getRandomBus();
    }
}
