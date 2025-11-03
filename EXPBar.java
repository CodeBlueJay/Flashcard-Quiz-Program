import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



public class EXPBar extends Application {
    static VBox root;

    @Override
    public void start(Stage stage) {
            try {
                root = new VBox();
                Scene scene = new Scene(root, 400, 300);
                stage.setScene(scene);
                stage.setTitle("EXP Bar");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void load() {

        ProgressBar pb = new ProgressBar();

        root.getChildren().add(pb);
    }
}