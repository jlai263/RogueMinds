package GameStart;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Manages and plays sound effects with volume control for the application.
 * <p>
 * This class is responsible for playing sound files and adjusting their volume levels based on the application's
 * current settings. It uses a matrix of boolean values to determine the volume level for sound effects and music.
 * 
 * @author      Justin Edwin Lee
 * @version     1.3
 * @since       1.0
 */
public class SoundManager {
    private boolean[][] volumeStates;
  
    /**
     * Constructs a SoundManager with specified volume states.
     * 
     * @param volumeStates A matrix of boolean values representing the on/off state of different volume levels.
     */
    public SoundManager(boolean[][] volumeStates) {
        this.volumeStates = volumeStates;
    }

    /**
     * Plays a sound file specified by its filename, adjusting its volume based on current settings.
     * 
     * @param soundFileName The file name of the sound to be played.
     */
    public void playSound(String soundFileName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(soundFileName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            float masterVolumeLevel = calculateVolumeLevel();
            float sfxVolumeLevel = calculateSFXVolumeLevel();
            float finalVolumeLevel = masterVolumeLevel * sfxVolumeLevel;
            adjustVolume(clip, finalVolumeLevel);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    /**
     * Calculates the master volume level based on the application's volume states.
     * 
     * @return The calculated volume level as a float.
     */
    private float calculateVolumeLevel() {
        int vOnCount = 0;
        for (boolean state : volumeStates[0]) {
            if (state) vOnCount++;
        }
        return vOnCount / 10f;
    }
    /**
     * Calculates the volume level for sound effects (SFX) based on the application's volume states.
     * 
     * @return The calculated SFX volume level as a float.
     */
    private float calculateSFXVolumeLevel() {
        int vOnCount = 0;
        for (boolean state : volumeStates[2]) {
            if (state) vOnCount++;
        }
        return vOnCount / 10f;
    }
    /**
     * Adjusts the volume of a given Clip to a specified level.
     * 
     * @param clip The Clip whose volume will be adjusted.
     * @param volumeLevel The target volume level as a float between 0.0 (mute) and 1.0 (maximum).
     */
    private void adjustVolume(Clip clip, float volumeLevel) {
        if (volumeLevel < 0f || volumeLevel > 1f) {
            throw new IllegalArgumentException("Volume percentage must be between 0.0 and 1.0");
        }
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volumeLevel) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
        } else {
            System.err.println("Volume control not supported");
        }
    }
}
