package service.fillCollection;

import service.fillCollection.fillStrategy.IFillCollection;

import java.util.HashMap;
import java.util.List;

public class FillCollection {
    private final IFillCollection fillCollection;

    public FillCollection(IFillCollection fillCollection) {
        this.fillCollection = fillCollection;
    }

    public List<HashMap<String, Object>> fillCollection() {
        return this.fillCollection.getFillCollection();
    }
}
