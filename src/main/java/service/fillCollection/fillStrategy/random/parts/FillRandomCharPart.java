package service.fillCollection.fillStrategy.random.parts;

public class FillRandomCharPart extends FillRandomPart {
    public FillRandomCharPart(int len) {
        super(new RandomCharPartStrategy(), len);
    }

}
