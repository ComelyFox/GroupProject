package service.fillCollection.fillStrategy.random.parts;

public class FillRandomPart {
    IRandomPart random;
    int length;

    public FillRandomPart(IRandomPart random, int length) {
        this.random = random;
        this.length = length;
    }

    public StringBuilder random() {
        return this.random.randomPart(this.length);
    }
}
