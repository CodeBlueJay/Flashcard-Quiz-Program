import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;

public class Soundplayer {
    File correct = new File("sounds/correct.mp3");
    File wrong = new File("sounds/wrong.mp3");
    String correctUri = correct.toURI().toString();
    String wrongUri = wrong.toURI().toString();
    Media correctSound = new Media(correctUri);
    Media wrongSound = new Media(wrongUri);
    MediaPlayer correctPlayer = new MediaPlayer(correctSound);
    MediaPlayer wrongPlayer = new MediaPlayer(wrongSound);

    private void playSound(MediaPlayer player) {
        player.stop();
        player.setStartTime(Duration.ZERO);
        player.play();
    }

    public void playCorrect() {
        playSound(correctPlayer);
    }

    public void playWrong() {
        playSound(wrongPlayer);
    }
}