package com.moodifyx;

/**
 * Represents a single music track with a title and file path.
 */
public class Song {
    private final String title;
    private final String filePath;

    /**
     * Constructs a Song instance.
     *
     * @param title    Display name for the song.
     * @param filePath Path to the audio file.
     */
    public Song(String title, String filePath) {
        this.title = title;
        this.filePath = filePath;
    }

    public String getTitle() {
        return title;
    }

    public String getFilePath() {
        return filePath;
    }
}


