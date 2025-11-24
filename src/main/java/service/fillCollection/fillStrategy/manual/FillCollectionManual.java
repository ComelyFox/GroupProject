package service.fillCollection.fillStrategy.manual;

import service.fillCollection.FillCollection;
import service.fillCollection.fillStrategy.IFillCollection;

public class FillCollectionManual extends FillCollection {
    public FillCollectionManual(String model, int number, int mileage) {
        super(new FillManualStrategy(model, number, mileage));
    }
}
