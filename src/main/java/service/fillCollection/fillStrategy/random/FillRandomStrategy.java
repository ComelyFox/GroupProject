package service.fillCollection.fillStrategy.random;

import service.fillCollection.fillStrategy.IFillCollection;

import java.util.*;

public class FillRandomStrategy implements IFillCollection {
    private final HashMap<String, Object> mapEntityBus;
    private final List<HashMap<String, Object>> listMapEntityBus;
    int quantityBus;
    List<String> listModels = List.of("Volvo", "Mercedes", "Scania", "Renault", "Kamaz", "DAF", "Man",
            "Iveco", "Isuzu", "SITRAK", "Shacman", "JAC");

    public FillRandomStrategy(){
        this.mapEntityBus = new HashMap<>();
        this.listMapEntityBus = new ArrayList<>();
        this.quantityBus = 1;
    }
    
    public void setQuantityBus(int quantityBus) {
        this.quantityBus = quantityBus;
    }

    private String getRandomModel() {
        return listModels.get(new Random().nextInt(listModels.size()));
    }

    private int getRandomInt() {
        Random rand = new Random();
        return rand.nextInt(1000000);
    }

    @Override
    public List<HashMap<String, Object>> fillCollection() {
        for (int i = 0; i < this.quantityBus; i++){
            mapEntityBus.put("model", getRandomModel());
            mapEntityBus.put("number", getRandomInt());
            mapEntityBus.put("mileage", getRandomInt());
            listMapEntityBus.add(new HashMap<>(mapEntityBus));
        }
        return listMapEntityBus;
    }

    @Override
    public void clearStrategyCollection() {
        quantityBus = 0;
        mapEntityBus.clear();
        listMapEntityBus.clear();
    }

}
