import java.util.Random;

public class Learn {
    private boolean text;
    private String[] words;
    private double[] weights;

    public Learn(boolean t, String[] w, double[] we) {
        this.text = t;
        this.words = w;
        this.weights = we;
    }

    public String weightedChoice(String[] items, double[] weights) {
        double totalWeight = 0.0;
        Random rand = new Random();
        double randomValue;
        for (double weight : weights) {
            totalWeight += weight;
        }
        randomValue = rand.nextDouble() * totalWeight;
        for (int i = 0; i < items.length; i++) {
            randomValue -= weights[i];
            if (randomValue <= 0.0) {
                return items[i];
            }
        }
        return null;
    }
}
