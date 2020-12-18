package cars_runner.model;

import javafx.scene.media.AudioClip;

import java.net.URI;

public class SoundEffects {

    private static double VOL_LOST1 = 10;
    private static double VOL_LOST2 = 10;
    private static double VOL_ADD = 30;
    private static double VOL_MAIN = 0.5;

    public static void playSoundLost1(URI sound) {
        AudioClip clip = new AudioClip(sound.toString());
        clip.setVolume(VOL_LOST1);
        clip.play();
    }

    public static void playSoundLost2(URI sound) {
        AudioClip clip = new AudioClip(sound.toString());
        clip.setVolume(VOL_LOST2);
        clip.play();
    }

    public static void playSoundAdd(URI sound) {
        AudioClip clip = new AudioClip(sound.toString());
        clip.setVolume(VOL_ADD);
        clip.play();
    }

    public static void playSoundMain(URI sound) {
        AudioClip clip = new AudioClip(sound.toString());
        clip.setVolume(VOL_MAIN);
        clip.play();
    }

}
