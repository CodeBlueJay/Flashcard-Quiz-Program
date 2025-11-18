public class Matching {
    private ArrayList<String> words;
    private ArrayList<String> meanings;
    private VBox bvox = new VBox(8);

    public Matching(ArrayList<String> w, ArrayList<String> m) {
        this.words = w;
        this.meanings = m;
        bvox.getChildren().addAll(wordButton, meaningButton);
        box.getChildren().add(bvox);
    }

    /** Needs to acount for: 
     * Creating buttons
     * Actions that occur on button click
     * Setting buttons equal to term / definition
     * Setting button pairs (one term on the left side that corresponds with term on the right side)
     * Removal of buttons that were clicked
     * E
     * Match action (clicking one button, then clicking another on the other side, without issue occuring)
     * Check action (Checking to see if buttons that were clicked are a correct pair)
     * */ 

    
    public void creatingButtons() {
        Button[] wordButton = new Button[words.length];
        Button[] meaningButton = new Button[meanings.length];
        if (words.length != meanings.length)
            Label error = new Label("There was a different amount of words and definitions.");
        else {
            for (int i = 0; i < words.length; i++) {
                wordButton[i] = new Button(words.get(i));
                meaningButton[i] = new Button(meanings.get(i));
            }
        }
    }
}