package main;

import main.Sound;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author          Darren Chen
 * @version         1.0
 * @since           1.0
 * This class contains JUnit tests for the Sound class.
 * <p>
 * It includes tests for loading sound files, playing, looping, and stopping playback, as well as handling
 * exceptions for invalid operations.
 */
class SoundTest {

    /**
     * Tests the {@code setFile} method of the Sound class.
     * Verifies that the method correctly loads sound files into the {@code Sound} object's {@code Clip} instance
     * based on the provided index.
     */
    @Test
    void testSetFile() {
        Sound sound = new Sound();

        // Test loading sound file at index 0
        sound.setFile(0);
        assertNotNull(sound.clip);

        // Test loading sound file at index 1
        sound.setFile(1);
        assertNotNull(sound.clip);

        // Test loading sound file at index 2
        sound.setFile(2);
        assertNotNull(sound.clip);

        // Test loading sound file at index 3
        sound.setFile(3);
        assertNotNull(sound.clip);

        // Test loading sound file at invalid index (should throw exception)
        assertThrows(IndexOutOfBoundsException.class, () -> sound.setFile(30));
    }

    /**
     * Tests the {@code play} method of the Sound class.
     * Verifies that the method correctly starts the playback of the loaded sound file.
     */
    @Test
    void testPlay() {
        Sound sound = new Sound();

        // Test playing loaded sound file
        sound.setFile(0);
        sound.play();

        assertTrue(sound.clip.isRunning());

        // Test playing sound file without loading (should throw exception)
        Sound soundWithoutFile = new Sound();
        assertThrows(NullPointerException.class, soundWithoutFile::play);
    }

    /**
     * Tests the {@code loop} method of the Sound class.
     * Verifies that the method correctly starts looping the playback of the loaded sound file.
     */
    @Test
    void testLoop() {
        Sound sound = new Sound();

        // Test looping loaded sound file
        sound.setFile(0);
        sound.loop();

        assertTrue(sound.clip.isRunning());

        // Test looping sound file without loading (should throw exception)
        Sound soundWithoutFile = new Sound();
        assertThrows(NullPointerException.class, soundWithoutFile::loop);
    }

    /**
     * Tests the {@code stop} method of the Sound class.
     * Verifies that the method correctly stops the playback of the loaded sound file.
     */
    @Test
    void testStop() {
        Sound sound = new Sound();

        // Test stopping playback of loaded sound file
        sound.setFile(0);
        sound.play();
        sound.stop();

        assertFalse(sound.clip.isRunning());

        // Test stopping playback without loading sound file (should throw exception)
        Sound soundWithoutFile = new Sound();
        assertThrows(NullPointerException.class, soundWithoutFile::stop);
    }
}
