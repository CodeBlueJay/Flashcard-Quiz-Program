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
    private int currentLevel;

    public static EXPBarUI getInstance() {
        if (instance == null) {
            instance = new EXPBarUI(0, 100);
        }
        return instance;
    }

    public EXPBarUI(int initialXP, int max) {
        progressBar = new ProgressBar();
        xpLabel = new Label();
        level = new Label();
        currentLevel = 1;
        xpLabel.getStyleClass().add("xp-label");
        level.getStyleClass().add("level-label");
        setSpacing(8);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(4, 12, 4, 12));
        getChildren().addAll(level, progressBar, xpLabel);
        progressBar.progressProperty().bind(progress);
        currentXP.addListener((obs, oldV, newV) -> updateProgress());
        maxXP.addListener((obs, oldV, newV) -> updateProgress());
        currentXP.set(initialXP);
        maxXP.set(max);
        updateProgress();
    }

    private void updateProgress() {
        int cur = Math.max(0, currentXP.get());
        int max = Math.max(1, maxXP.get());
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
                newXP -= maxXP.get();
                levelUp();
            }
            currentXP.set(newXP);
            updateProgress();
        });
    }

    private void levelUp() {
        currentLevel++;
        int old = maxXP.get();
        if (currentLevel <= 100)
            maxXP.set((int)(old + 100 - currentLevel * 0.5));
        else 
            maxXP.set((int)(old + 9));
        updateProgress();
    }

    public void setXP(int xp) {
        currentLevel = 1; 
        maxXP.set(100); 
        currentXP.set(xp);
        updateProgress();
    }

    public void setMaxXP(int max) {
        maxXP.set(max);
        updateProgress();
    }

    public double getProgress() { 
        return progress.get(); 
    }
    
    public DoubleProperty progressProperty() { 
        return progress; 
    }
    
    public int getXP() { 
        return currentXP.get(); 
    }
    
    public int getMaxXP() { 
        return maxXP.get(); 
    }
}