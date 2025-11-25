package service.fillCollection.fillStrategy.file;

import service.fillCollection.FillBus;

public class FillBusFromFile extends FillBus {
    public FillBusFromFile() {
        super(new FillFromFileStrategy());
    }
}
