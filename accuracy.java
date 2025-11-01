public class Accuracy {
    private String[] words;
    private String[] meanings;
    private int questionsCorrect;
    private double time;

    public Accuracy(String[] w, String[] m) {
        this.words = w;
        this.meanings = m;
        this.questionsCorrect = 0;
        this.time = 7.0;
    }
}