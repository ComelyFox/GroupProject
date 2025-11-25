package service.fillCollection.fillStrategy.random.parts;

import java.util.Random;
public class RandomIntPartStrategy implements IRandomPart {
    Random rand;
    StringBuilder string;

    public RandomIntPartStrategy() {
        rand = new Random();
        string = new StringBuilder();
    }
    @Override
    public StringBuilder randomPart(int length) {
        for (int i = 0; i < length; i++) {
            int randomChar = rand.nextInt(9);
            string.append(randomChar);
        }
        return string;

    }
}
