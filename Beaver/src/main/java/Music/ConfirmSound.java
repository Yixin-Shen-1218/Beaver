package Music;

import javafx.scene.media.AudioClip;

import java.net.URL;

/**
 * <p> The Class ConfirmSound load the ConfirmSound file and defines some methods to manipulate the AudioClip.
 * The Class ConfirmSound load the ConfirmSound file and defines some methods to manipulate the AudioClip, use singleton design pattern.
 *
 * @author Yixin SHEN
 * @version 1.0
 * @date 24/02/2021-20:37
 * @since 1.0
 */
public class ConfirmSound {
    /**
     * Defines the instance of the ConfirmSound class.
     */
    private static final ConfirmSound CONFIRM_SOUND = new ConfirmSound();
    /**
     * Defines the AudioClip music to load the music file.
     */
    private final AudioClip music;

    /**
     * The constructor of ConfirmSound class.
     * <p> Instantiate the ConfirmSound object.
     *
     * @author Yixin SHEN
     * @date 24/02/2021-20:37
     */
    private ConfirmSound() {
        // Load confirmation file
        URL url = this.getClass().getClassLoader().getResource("Music/confirmation.wav");
        assert url != null;
        music = new AudioClip(url.toExternalForm());
        music.setCycleCount(1);
        music.play();
    }

    /**
     * Return the instance of ConfirmSound.
     * <p> Return the instance of ConfirmSound.
     *
     * @return com.ae2dms.Music.AnimationBGM The instance of ConfirmSound.
     * @author Yixin SHEN
     * @date 24/02/2021-20:37
     */
    public static ConfirmSound getInstance() {
        return CONFIRM_SOUND;
    }

    /**
     * Play the music.
     * <p> Play the music.
     *
     * @author Yixin SHEN
     * @date 24/02/2021-20:37
     */
    public void playMusic() {
        music.play();
    }
}
