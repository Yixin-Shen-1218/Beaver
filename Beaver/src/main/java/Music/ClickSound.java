package Music;

import javafx.scene.media.AudioClip;

import java.net.URL;

/**
 * <p> The Class ClickSound load the ClickSound file and defines some methods to manipulate the AudioClip.
 * The Class ClickSound load the ClickSound file and defines some methods to manipulate the AudioClip, use singleton design pattern.
 *
 * @author Yixin SHEN
 * @version 1.0
 * @date 24/02/2021-20:37
 * @since 1.0
 */
public class ClickSound {
    /**
     * Defines the instance of the ClickSound class.
     */
    private static final ClickSound CLICK_SOUND = new ClickSound();
    /**
     * Defines the AudioClip music to load the music file.
     */
    private final AudioClip music;

    /**
     * The constructor of ClickSound class.
     * <p> Instantiate the ClickSound object.
     *
     * @author Yixin SHEN
     * @date 24/02/2021-20:37
     */
    private ClickSound() {
        // Load click file
        URL url = this.getClass().getClassLoader().getResource("Music/click.wav");
        assert url != null;
        music = new AudioClip(url.toExternalForm());
        music.setCycleCount(1);
        music.play();
    }

    /**
     * Return the instance of ClickSound.
     * <p> Return the instance of ClickSound.
     *
     * @return com.ae2dms.Music.AnimationBGM The instance of ClickSound.
     * @author Yixin SHEN
     * @date 24/02/2021-20:37
     */
    public static ClickSound getInstance() {
        return CLICK_SOUND;
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
