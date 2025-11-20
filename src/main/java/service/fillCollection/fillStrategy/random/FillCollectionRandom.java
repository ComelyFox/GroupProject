package service.fillCollection.fillStrategy.random;

import service.fillCollection.FillCollection;

public class FillCollectionRandom extends FillCollection {
    public FillCollectionRandom(int len) {
        super(new FillRandomStrategy(len));
    }
}
