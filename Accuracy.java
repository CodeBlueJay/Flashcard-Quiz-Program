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
    // more gui components
    private Label accuracylabel = new Label("Accuracy");
    private HBox container = new HBox(8);
    private TextField answer = new TextField();
    private Button submit = new Button("Submit");

    public Accuracy(ArrayList<String> w, ArrayList<String> m, ArrayList<Double> we, EXPBarUI exp) {
        words = w;
        meanings = m;
        weights = we;
        questionsCorrect = 0;
        time = 7.0;
        expBar = exp;
        setSpacing(10);
        setPadding(new Insets(16));
        answer.getStyleClass().add("answer");
        container.getChildren().addAll(accuracylabel, container, answer, submit);
        getChildren().add(container);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String ans = "";
                if (!(answer.getText() == null)) {
                    ans = answer.getText();
                } else {
                    ans = "";
                }
                answer.setText(ans);
            }
        });
    }

    
}