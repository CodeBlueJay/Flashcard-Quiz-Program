import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

public class EXPBarUI extends HBox {
    private static EXPBarUI instance;
    private final IntegerProperty currentXP = new SimpleIntegerProperty(0);
    private final IntegerProperty maxXP = new SimpleIntegerProperty(100);
    private final DoubleProperty progress = new SimpleDoubleProperty(0.0);
    private final ProgressBar progressBar;
    private final Label xpLabel;
    private final Label level;
    private int currentLevel = 1;

    public static EXPBarUI getInstance() {
        if (instance == null) {
            instance = new EXPBarUI(0, 100);
        }
        return instance;
    }

    private EXPBarUI(int initialXP, int max) {
        // Initialize visual components first
        progressBar = new ProgressBar(0);
        xpLabel = new Label();
        level = new Label();

        // Set up visual properties
        xpLabel.getStyleClass().add("xp-label");
        level.getStyleClass().add("level-label");
        
        // Set up layout
        setSpacing(8);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(4, 12, 4, 12));
        getChildren().addAll(level, progressBar, xpLabel);

        // Set up bindings and listeners
        progressBar.progressProperty().bind(progress);
        currentXP.addListener((obs, oldV, newV) -> updateProgress());
        maxXP.addListener((obs, oldV, newV) -> updateProgress());

        // Initialize state
        currentXP.set(initialXP);
        maxXP.set(max);
        
        // Update visual state
        updateProgress();
    }

    private void updateProgress() {
        int cur = Math.max(0, currentXP.get());
        int max = Math.max(1, maxXP.get());

        int curLvl = currentLevel;
        while (cur >= max) {
            cur -= max;
            currentLevel++;
        }
        progress.set((double) cur / max);
        updateLabel();
    }

    private void updateLabel() {
        xpLabel.setText(String.format("%d / %d XP", currentXP.get(), maxXP.get()));
        level.setText("Level " + currentLevel);
    }

    public void addXP(int amount) {
        Platform.runLater(() -> {
            int newXP = currentXP.get() + amount;
            while (newXP >= maxXP.get()) {
                levelUp();
                newXP -= maxXP.get();
            }
            currentXP.set(Math.max(0, newXP));
            updateLabel();
        });
    }

    private void levelUp() {
        currentLevel++;
        int oldMax = maxXP.get();
        maxXP.set((int)(oldMax * 1.5)); // Increase max XP by 50% each level
        updateLabel();
    }

    public void setXP(int xp) {
        currentLevel = 1; // Reset level when explicitly setting XP
        maxXP.set(100);  // Reset max XP back to initial value
        currentXP.set(Math.max(0, xp));
        updateLabel();
    }

    public void setMaxXP(int max) {
        maxXP.set(Math.max(1, max));
        updateLabel();
    }

    public double getProgress() { return progress.get(); }
    public DoubleProperty progressProperty() { return progress; }
    public int getXP() { return currentXP.get(); }
    public int getMaxXP() { return maxXP.get(); }
}