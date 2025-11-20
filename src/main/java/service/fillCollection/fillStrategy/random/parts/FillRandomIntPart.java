package service.fillCollection.fillStrategy.random.parts;

public class FillRandomIntPart extends FillRandomPart {
    public FillRandomIntPart(int len) {
        super(new RandomIntPartStrategy(), len);
    }
}
