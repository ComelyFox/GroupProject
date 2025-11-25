package service.fillCollection.fillStrategy.manual;

import service.fillCollection.FillBus;

public class FillBusManual extends FillBus {
    public FillBusManual() {
        super(new FillManualStrategy());
    }
}
