package service.fillCollection.fillStrategy;

public class FillRandomCharPart extends FillRandomPart{
    public FillRandomCharPart(int len) {
        super(new RandomCharPartStrategy(), len);
    }

}
