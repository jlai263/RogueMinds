package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
/**
 * Manages sound effects and background music for the game.
 * <p>
 * This class handles the loading, playing, looping, and stopping of sound files. It is capable of managing
 * multiple sound files through an array of URLs and dynamically loading and playing these sounds based
 * on game events or actions.
 * 
 * @author Gurshaan Gill
 * @version 1.3
 * @since 1.0
 */
public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
  
    /**
     * Initializes the Sound object and loads predefined sound files into the URL array.
     */
    public Sound(){
        soundURL[0] = getClass().getResource("/sound/BackgroundMusic.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/fanfare.wav");
        soundURL[3] = getClass().getResource("/sound/unlock.wav");
    }
  
    /**
     * Loads a sound file into the Clip from the soundURL array using the specified index.
     * 
     * @param i The index of the sound file in the soundURL array to be loaded.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public void setFile(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= soundURL.length) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds for length " + soundURL.length);
        }
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Plays the loaded sound file from the beginning.
     */
    public void play(){clip.start();}

      /**
     * Loops the currently loaded sound file continuously.
     */
    public void loop(){clip.loop(Clip.LOOP_CONTINUOUSLY);}
  
    /**
     * Stops the playback of the currently loaded sound file.
     */
    public void stop(){ clip.stop();}

}
