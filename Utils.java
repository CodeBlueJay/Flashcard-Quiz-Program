import java.util.ArrayList;
import java.util.Random;

public class Utils {
    private static final Random RNG = new Random();

    public static int weightedIndex(ArrayList<Double> weights, ArrayList<String> words) {
        if (weights == null || words == null || words.size() == 0 || weights.size() < words.size()) return -1;
        double totalWeight = 0.0;
        for (double w : weights) totalWeight += w;
        if (totalWeight <= 0.0) return -1;
        double randomValue = RNG.nextDouble() * totalWeight;
        for (int i = 0; i < words.size(); i++) {
            randomValue -= weights.get(i);
            if (randomValue <= 0.0) return i;
        }
        return words.size() - 1;
    }
}
