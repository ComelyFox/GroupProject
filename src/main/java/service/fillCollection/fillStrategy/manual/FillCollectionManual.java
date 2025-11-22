package service.fillCollection.fillStrategy.manual;

import service.fillCollection.FillCollection;
import service.fillCollection.fillStrategy.IFillCollection;

public class FillCollectionManual extends FillCollection {
    public FillCollectionManual(String number, String model, int mileage) {
        super(new FillManualStrategy(number, model, mileage));
    }
}
