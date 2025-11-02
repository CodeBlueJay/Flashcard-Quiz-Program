import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {
    private BorderPane root;
    // TEMPORARY TEST ARRAYS
    ArrayList<String> test_terms = new ArrayList<>(Arrays.asList("Jayden", "Med", "Owen"));
    ArrayList<String> test_definitions = new ArrayList<>(Arrays.asList("J", "M", "O"));

    Flashcards flashcards = new Flashcards(test_terms, test_definitions);
    ArrayList<ArrayList<String>> set = flashcards.getFlashcardSet();
    ArrayList<String> terms = set.get(0);
    ArrayList<String> definitions = set.get(1);
    ArrayList<Double> weights = flashcards.getWeights();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Flashcard Program");

        VBox menu = new VBox(10);
        menu.setPadding(new Insets(12));
        Button homeBtn = new Button("Home");
        Button setsBtn = new Button("Sets");
        Button learnBtn = new Button("Learn");
        Button matchingBtn = new Button("Matching");
        Button bossBtn = new Button("Boss Battle");
        Button accuracyBtn = new Button("Accuracy");

        homeBtn.getStyleClass().addAll("nav-button", "primary");
        setsBtn.getStyleClass().addAll("nav-button", "accent");
        learnBtn.getStyleClass().addAll("nav-button", "danger");
        matchingBtn.getStyleClass().addAll("nav-button", "danger");
        bossBtn.getStyleClass().addAll("nav-button", "danger");
        accuracyBtn.getStyleClass().addAll("nav-button", "danger");

        menu.getChildren().addAll(homeBtn, setsBtn, learnBtn, matchingBtn, bossBtn, accuracyBtn);
        root = new BorderPane();
        root.setLeft(menu);
        root.setCenter(buildHomeScreen());
        homeBtn.setOnAction(e -> root.setCenter(buildHomeScreen()));
        learnBtn.setOnAction(e -> root.setCenter(buildLearnScreen()));
        matchingBtn.setOnAction(e -> root.setCenter(buildMatchingScreen()));
        bossBtn.setOnAction(e -> root.setCenter(buildBossScreen()));
        accuracyBtn.setOnAction(e -> root.setCenter(buildAccuracyScreen()));
        setsBtn.setOnAction(e -> root.setCenter(buildSetsScreen()));
        Scene scene = new Scene(root, 900, 650);
        File css = new File("styles.css");
        if (css.exists()) {
            scene.getStylesheets().add(css.toURI().toString());
        }
        stage.setScene(scene);
        stage.show();
    }

    private Node buildHomeScreen() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(16));
        box.getChildren().addAll(
            new Label("Flashcard Program Home"),
            new Label("Modes and Sets on the left menu")
        );
        return box;
    }

    private Node buildLearnScreen() {
        Learn learn = new Learn(terms, weights, definitions);
        VBox box = new VBox(10);
        box.setPadding(new Insets(16));
        Label title = new Label("Learn");
        Label prompt = new Label("Temporary Button that shows a random term (Currently all equal chance because weights are 1)");
        Label current = new Label("");
        ToggleButton text = new ToggleButton("Text");
        text.getStyleClass().add("switch-button");
        text.setOnAction(e -> {
            learn.switchText();
            text.setText(learn.isText() ? "Multi-Select" : "Text");
        });
        Button next = new Button("Word");
        next.setOnAction(e -> {
            ArrayList<String> chosen = learn.weightedChoice();
            current.setText("Word: " + chosen.get(0) + "\nDefinition: " + chosen.get(1));
        });
        box.getChildren().addAll(title, text, prompt, next, current);
        return box;
    }

    private Node buildMatchingScreen() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(16));
        box.getChildren().addAll(
            new Label("Matching"),
            new Label("Build two columns of terms/definitions and connect widget of some sort.")
        );
        return box;
    }

    private Node buildBossScreen() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(16));
        box.getChildren().addAll(
            new Label("Boss Battle"),
            new Label("Answer correctly to damage the boss. Add healthbar (Owen's job).")
        );
        return box;
    }

    private Node buildAccuracyScreen() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(16));
        box.getChildren().addAll(
            new Label("Accuracy Challenge"),
            new Label("Typing-only, strict correctness, decreasing time window.")
        );
        return box;
    }

    private Node buildSetsScreen() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(16));
        box.getChildren().addAll(
            new Label("Sets"),
            new Label("Manage your flashcard sets here.")
        );
        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }
}