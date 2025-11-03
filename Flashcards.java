import java.util.ArrayList;

public class Flashcards {
    public static ArrayList<Flashcards> IDs = new ArrayList<Flashcards>();
    private ArrayList<ArrayList<String>> flashcardSet = new ArrayList<ArrayList<String>>();
    private ArrayList<Double> weights = new ArrayList<Double>();

    public Flashcards(ArrayList<String> terms, ArrayList<String> definitions){
        flashcardSet.add(terms);
        flashcardSet.add(definitions);
        for (int i = 0; i < terms.size(); i++) {
            weights.add(1.0);
        }
    }

    // Sorts flashcards alphabetically, using terms as alphabetical reference
    public void sortFlashcards(){
        ArrayList<String> tempT = new ArrayList<String>();
        ArrayList<String> tempD = new ArrayList<String>();
        int i = 0;
        String min;
        while (tempT.size() < flashcardSet.get(0).size()){
            min = (flashcardSet.get(0)).get(i);
            for (String term: flashcardSet.get(0)){
                if (min.compareToIgnoreCase(term) < 0){
                    min = term;
                }
            }
            tempD.add(flashcardSet.get(1).get(flashcardSet.get(0).indexOf(min)));
            tempT.add(flashcardSet.get(0).remove(min));
            i++;
        }
        flashcardSet.clear();
        flashcardSet.add(tempT);
        flashcardSet.add(tempD);
    }

    //Returns current flashcard set
    public ArrayList<ArrayList<String>> getFlashcardSet(){
        return flashcardSet;
    }

    // dont know what the param will be, but updates flashcard set by either adding, removing, or changing indices.
    public void updateFlashcardSet(){

    }

    // adds given flashcard to IDs
    public void addFlashcardSet(Flashcards card){
        IDs.add(card);
    }
    
    // Returns flashcards object that is stored at given index
    public Flashcards getFlashcardObject(int index){
        return (IDs.get(index)); 
    }

    // Returns individual flashcard at given index. Will always follow the form [Term, Definition].
    public ArrayList<String> getFlashcard(int index){
        ArrayList<String> flashcard = new ArrayList<String>();
        flashcard.add((flashcardSet.get(0)).get(index));
        flashcard.add((flashcardSet.get(1)).get(index));
        return flashcard;
    }

    public ArrayList<Double> getWeights() {
        return weights;
    }
}