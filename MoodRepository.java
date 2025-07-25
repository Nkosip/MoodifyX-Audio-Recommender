package com.moodifyx;

import java.util.*;

/**
 * Handles mapping of moods to associated songs.
 * Provides access to mood categories and their respective audio tracks.
 */
public class MoodRepository {
    // Stores mood name as key and list of songs associated with that mood as value
    private final Map<String, List<Song>> moodSongs = new HashMap<>();

    /**
     * Constructor initializes the repository with default moods and songs.
     */
    public MoodRepository() {
        populateSongsByMood();
    }

    /**
     * Populates the internal map with predefined moods and song file paths.
     */
    private void populateSongsByMood() {
        Map<String, String[]> moodsAndFiles = Map.of(
            "Happy", new String[]{"happygirl.wav", "happygirlie.wav"},
            "Angry", new String[]{"Angry.wav", "Angrygirlie.wav"},
            "Chill", new String[]{"chillgirl.wav", "chilliirlie.wav"},
            "Mysterious", new String[]{"mysteriousgirl.wav", "mysteriousgirlie.wav"},
            "Moody", new String[]{"moodygirl.wav", "moodyirlie.wav"},
            "Practise", new String[]{"practicegirl.wav", "practicegirlie.wav"},
            "Romantic", new String[]{"romanticgirl.wav", "romanticgirlie.wav"},
            "Peaceful", new String[]{"peacefulgirl.wav", "peacefulgirlie.wav"},
            "Positive", new String[]{"positivegirl.wav", "positivegirlie.wav"}
        );

        // Populate the moodSongs map
        moodsAndFiles.forEach((mood, files) -> moodSongs.put(mood, convertToSongs(files)));
    }

    /**
     * Converts an array of filenames into a list of Song objects.
     *
     * @param fileNames Array of file names for the given mood.
     * @return List of Song objects.
     */
    private List<Song> convertToSongs(String[] fileNames) {
        List<Song> songs = new ArrayList<>();
        for (String file : fileNames) {
            String title = createTitleFromFilename(file);
            songs.add(new Song(title, "music/" + file));
        }
        return songs;
    }

    /**
     * Generates a clean song title from a filename.
     *
     * @param fileName WAV file name.
     * @return Cleaned and capitalized song title.
     */
    private String createTitleFromFilename(String fileName) {
        String nameWithoutExtension = fileName.replace(".wav", "");
        String cleaned = nameWithoutExtension.replaceAll("girlie|girl", "").trim();
        return capitalize(cleaned) + " Vibe";
    }

    /**
     * Capitalizes the first letter of a given string.
     *
     * @param input Input string.
     * @return Capitalized string.
     */
    private String capitalize(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    /**
     * Retrieves the songs associated with a specific mood.
     *
     * @param mood The mood to retrieve songs for.
     * @return List of songs, or a default list if mood is not found.
     */
    public List<Song> getSongsForMood(String mood) {
        return moodSongs.getOrDefault(mood, List.of(new Song("Default", "music/happygirl.wav")));
    }

    /**
     * Gets all available mood categories.
     *
     * @return Set of mood names, sorted alphabetically.
     */
    public Set<String> getAllMoods() {
        return new TreeSet<>(moodSongs.keySet()); // alphabetically ordered for UI
    }
}
