package service.fillCollection.fillStrategy;

import java.util.Random;

public class RandomCharPartStrategy implements IRandomPart {
    Random rand;
    StringBuilder string;

    public RandomCharPartStrategy() {
        rand = new Random();
        string = new StringBuilder();
    }
    @Override
    public StringBuilder randomPart(int length) {
        for (int i = 0; i < length; i++) {
            char randomChar = (char) ('A' + rand.nextInt(26));
            string.append(randomChar);
        }
        return string;

    }
}
