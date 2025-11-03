import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

// An xp bar that can be changed manually.
// public class EXPBar extends Application {
 
//     @Override
//     public void start(Stage stage) {
//         Group root = new Group();
//         Scene scene = new Scene(root);
//         stage.setScene(scene);
//         stage.setTitle("Progress Controls");
 
//         final Slider slider = new Slider();
//         slider.setMin(0);
//         slider.setMax(50);
         
//         final ProgressBar pb = new ProgressBar(0);
//         final ProgressIndicator pi = new ProgressIndicator(0);
 
//         slider.valueProperty().addListener(
//             (ObservableValue<? extends Number> ov, Number old_val, 
//             Number new_val) -> {
//                 pb.setProgress(new_val.doubleValue()/50);
//                 pi.setProgress(new_val.doubleValue()/50);
//         });
 
//         final HBox hb = new HBox();
//         hb.setSpacing(5);
//         hb.setAlignment(Pos.CENTER);
//         hb.getChildren().addAll(slider, pb, pi);
//         scene.setRoot(hb);
//         stage.show();
//     }
//     public static void main(String[] args) {
//         launch(args);
//     }
// }

public class EXPBar extends Application {
    static VBox root;

    @Override
    public void start(Stage stage) {
            try {
                root = new VBox();
                Scene scene = new Scene(root,1000,300);
                java.net.URL css = getClass().getResource("application.css");
            if (css != null) {
                scene.getStylesheets().add(css.toExternalForm());
            } else {
                // fallback to project file, or log message
                java.io.File fallback = new java.io.File("styles.css");
                if (fallback.exists()) {
                    scene.getStylesheets().add(fallback.toURI().toString());
                } else {
                    System.out.println("Stylesheet not found: application.css or styles.css");
                }
            }
                stage.setScene(scene);
                stage.setTitle("EXP Bar");
                // build UI (including test buttons)
                load();
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void load() {
        // Use the shared EXPBarUI component if available
        try {
            EXPBarUI expBar = EXPBarUI.getInstance();
            root.setAlignment(Pos.CENTER);
            root.getChildren().add(expBar);

            // Add test buttons to give XP
            HBox buttons = new HBox(10);
            buttons.setAlignment(Pos.CENTER);
            Button add10 = new Button("Add 10 XP");
            add10.setOnAction(e -> expBar.addXP(10));
            Button add50 = new Button("Add max XP");
            add50.setOnAction(e -> expBar.addXP(expBar.getMaxXP()));
            
            Button reset = new Button("Reset XP");
            reset.setOnAction(e -> expBar.setXP(0));
            expBar.setMaxXP(100);
            
            buttons.getChildren().addAll(add10, add50, reset);
            root.getChildren().add(buttons);
        } catch (Exception e) {
            System.err.println("Failed to load XP bar: " + e.getMessage());
            e.printStackTrace();
            
            // Fallback to simple progress bar
            ProgressBar pb = new ProgressBar();
            pb.setProgress(0.50f);
            root.setAlignment(Pos.CENTER);
            root.getChildren().add(pb);
        }
    }
}
