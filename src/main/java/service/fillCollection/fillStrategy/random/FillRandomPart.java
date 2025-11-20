package service.fillCollection.fillStrategy;

public class FillRandomPart {
    IRandomPart random;
    int length;

    FillRandomPart(IRandomPart random, int length) {
        this.random = random;
        this.length = length;
    }

    public StringBuilder random() {
        return this.random.randomPart(this.length);
    }







//    public static StringBuilder randomCharPart(int length) {
//        Random rand = new Random();
//        StringBuilder string = new StringBuilder();
//        for (int i = 0; i < length; i++) {
//            char randomChar = (char) ('A' + rand.nextInt(26));
//            string.append(randomChar);
//        }
//        return string;
//    }
//
//    public static StringBuilder randomIntPart(int length) {
//        StringBuilder string = new StringBuilder();
//        Random rand = new Random();
//        for (int i = 0; i < length; i++) {
//            int randomChar = rand.nextInt(9);
//            string.append(randomChar);
//        }
//        return string;
//    }



//    public static void main(String[] args) {
//        StringBuilder string = new StringBuilder();
//        string.append(randomCharPart(1)).append(randomIntPart(3)).append(randomCharPart(2));
//
//
//        System.out.println(string);
//    }
}
