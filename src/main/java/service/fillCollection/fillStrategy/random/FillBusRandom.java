package service.fillCollection.fillStrategy.random;

import service.fillCollection.FillBus;

public class FillBusRandom extends FillBus {
    public FillBusRandom() {
        super(new FillRandomStrategy());
    }
}
