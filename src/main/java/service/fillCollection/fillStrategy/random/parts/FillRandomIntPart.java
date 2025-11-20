package service.fillCollection.fillStrategy;

public class FillRandomIntPart extends FillRandomPart {
    public FillRandomIntPart(int len) {
        super(new RandomIntPartStrategy(), len);
    }
}
