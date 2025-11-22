package service.fillCollection.fillStrategy.file;

import service.fillCollection.FillCollection;
import service.fillCollection.fillStrategy.IFillCollection;

public class FillCollectionFromFile extends FillCollection {
    public FillCollectionFromFile(String path) {
        super(new FillFromFileStrategy(path));
    }
}
