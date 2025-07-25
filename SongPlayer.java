package com.moodifyx;

import java.io.File;
import javax.sound.sampled.*;

/**
 * Utility class to handle audio playback using Java Sound API.
 */
public class SongPlayer {
    private static Clip clip;

    /**
     * Plays the audio file at the specified path.
     *
     * @param filePath Path to the .wav file.
     */
    public static void play(String filePath) {
        stop(); // Stop any existing clip before starting a new one
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }

    /**
     * Stops the currently playing audio clip, if any.
     */
    public static void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}

