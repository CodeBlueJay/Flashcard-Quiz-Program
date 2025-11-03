import java.util.ArrayList;

public class Flashcards {
    public static ArrayList<Flashcards> IDs = new ArrayList<Flashcards>();
    public static ArrayList<String> titles = new ArrayList<String>();
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
        ArrayList<String> srcTerms = new ArrayList<String>(flashcardSet.get(0));
        ArrayList<String> srcDefs  = new ArrayList<String>(flashcardSet.get(1));
        ArrayList<Double> srcWts   = new ArrayList<Double>(weights);
        ArrayList<String> tempT = new ArrayList<String>();
        ArrayList<String> tempD = new ArrayList<String>();
        ArrayList<Double> tempW = new ArrayList<Double>();

        while (!srcTerms.isEmpty()){
            int minIndex = 0;
            String min = srcTerms.get(0);
            for (int i = 1; i < srcTerms.size(); i++){
                String term = srcTerms.get(i);
                if (term.compareToIgnoreCase(min) < 0){
                    min = term;
                    minIndex = i;
                }
            }
            tempT.add(srcTerms.remove(minIndex));
            tempD.add(srcDefs.remove(minIndex));
            tempW.add(srcWts.remove(minIndex));
        }
        flashcardSet.clear();
        flashcardSet.add(tempT);
        flashcardSet.add(tempD);
        weights = tempW;
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
        titles.add("Set " + IDs.size());
    }
    public void addFlashcardSet(Flashcards card, String title){
        IDs.add(card);
        titles.add(title);
    }
    
    // Returns flashcards object that is stored at given index
    public Flashcards getFlashcardObject(int index){
        return (IDs.get(index)); 
    }

    public ArrayList<Flashcards> getAllFlashcardSets(){
        return IDs;
    }

    public String getTitle(int index){
        return titles.get(index);
    }

    public void setTitle(int index, String title){
        titles.set(index, title);
    }

    public ArrayList<String> getAllTitles(){
        return titles;
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