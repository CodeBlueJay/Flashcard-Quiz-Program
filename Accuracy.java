import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;

public class Accuracy extends VBox {
    private ArrayList<String> words;
    private ArrayList<String> meanings;
    private ArrayList<Double> weights;
    private int questionsCorrect;
    private double time;
    private EXPBarUI expBar;

    public Accuracy(ArrayList<String> w, ArrayList<String> m, ArrayList<Double> we, EXPBarUI exp) {
        words = w;
        meanings = m;
        weights = we;
        questionsCorrect = 0;
        time = 7.0;
        expBar = exp;
    }


}