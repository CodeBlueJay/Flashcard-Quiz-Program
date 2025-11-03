import java.util.Random;
import java.util.ArrayList;

public class Learn {
    private boolean text;
    private ArrayList<String> words;
    private ArrayList<Double> weights;
    private ArrayList<String> definitions;

    public Learn(ArrayList<String> w, ArrayList<Double> we, ArrayList<String> d) {
        this.text = false;
        this.words = w;
        this.weights = we;
        this.definitions = d;
    }

    public ArrayList<String> weightedChoice() {
        double totalWeight = 0.0;
        Random rand = new Random();
        double randomValue;
        for (double weight : weights) {
            totalWeight += weight;
        }
        randomValue = rand.nextDouble() * totalWeight;
        for (int i = 0; i < words.size(); i++) {
            randomValue -= weights.get(i);
            if (randomValue <= 0.0) {
                ArrayList<String> pair = new ArrayList<>();
                pair.add(words.get(i));
                pair.add(definitions.get(i));
                return pair;
            }
        }
        return null;
    }

    public boolean isText() {
        return text;
    }

    public void switchText() {
        this.text = !text;
    }
}
