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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Accuracy extends VBox {
    private ArrayList<String> words;
    private ArrayList<String> meanings;
    private ArrayList<Double> weights;
    private int questionsCorrect;
    private double time;
    private EXPBarUI expBar;
    private String correctAnswer;
    private String ans;
    private Timeline timeline;
    private boolean started;
    // more gui components
    private Label accuracylabel = new Label("Accuracy");
    private VBox container = new VBox(8);
    private Label feedback = new Label();
    private TextField answer = new TextField();
    private Button submit = new Button("Submit");
    private Button start = new Button("Start");
    private Label showTimer = new Label();
    private Label score = new Label("Score: " + questionsCorrect);
    private Label definition = new Label("Definition: ");
    private Soundplayer soundplayer = new Soundplayer();
    Font microwave;
    private HBox timerButtons = new HBox(4);
    {
        microwave = Font.loadFont("file:fonts/microwave.ttf", 36);
    }
    
    public Accuracy(ArrayList<String> w, ArrayList<String> m, ArrayList<Double> we, EXPBarUI exp) {
        words = w;
        meanings = m;
        weights = we;
        questionsCorrect = 0;
        time = 7.0;
        started = false;
        expBar = exp;
        timeline = new Timeline();
        setSpacing(10);
        setPadding(new Insets(16));
        answer.getStyleClass().add("answer");
        answer.setDisable(!started);
        showTimer.setText(String.format("%.2f", time));
        showTimer.getStyleClass().add("timer");
        score.getStyleClass().add("score");
        submit.setDisable(!started);
        if (microwave != null) {
            showTimer.setFont(microwave);
        }
        container.getChildren().addAll(answer, definition, feedback, submit);
        timerButtons.getChildren().addAll(showTimer, start);
        getChildren().addAll(accuracylabel, score, timerButtons, container);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ans = "";
                if (!(answer.getText() == null)) {
                    ans = answer.getText();
                } else {
                    ans = "";
                }
                answer.setText(ans);
                if (ans.toLowerCase().equals(correctAnswer.toLowerCase())) {
                    questionsCorrect++;
                    score.setText("Score: " + questionsCorrect);
                    expBar.addXP(10);
                    feedback.setText("Correct!");
                    soundplayer.playCorrect();
                } else {
                    feedback.setText("Incorrect! The correct answer was: " + correctAnswer);
                    soundplayer.playWrong();
                }
            }
        });
        timer();
    }
    public void timer() {
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainLoop();
                started = true;
                answer.setDisable(false);
                submit.setDisable(false);
                //start.setDisable(true);
                answer.requestFocus();
                if (timeline != null)
                    timeline.stop();
                timeline = new Timeline();
                timeline.getKeyFrames().add(  
                    new KeyFrame(Duration.seconds(time + 1), 
                        e -> { System.out.println("Time's up!"); }
                    ));
                timeline.play();
            }
        });
    }

    private void mainLoop() {
        int index = Utils.weightedIndex(weights, words);
        correctAnswer = words.get(index);
        definition.setText("Definition: " + meanings.get(index));
        
    }
}