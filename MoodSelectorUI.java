package com.moodifyx;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

/**
 * Provides a graphical interface for users to select a mood
 * and play a randomly selected song corresponding to that mood.
 */
public class MoodSelectorUI extends JFrame {
    private final MoodRepository moodRepository = new MoodRepository();

    /**
     * Constructor initializes the GUI window and components.
     */
    public MoodSelectorUI() {
        configureWindow();
        buildUserInterface();
        setVisible(true);
    }

    /**
     * Sets basic properties of the application window.
     */
    private void configureWindow() {
        setTitle("MoodifyX - Vibe Matcher");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

     /**
     * Builds and adds UI components: mood dropdown, label, and button.
     */
    private void buildUserInterface() {
        Set<String> moods = moodRepository.getAllMoods();
        JComboBox<String> moodDropdown = new JComboBox<>(moods.toArray(new String[0]));
        JButton playButton = new JButton("Recommended Music");

        playButton.addActionListener(e -> {
            String selectedMood = (String) moodDropdown.getSelectedItem();
            if (selectedMood != null) {
                showMoodPlayerDialog(selectedMood);
            }
        });

        JPanel uiPanel = new JPanel();
        uiPanel.add(new JLabel("Select Your Mood:"));
        uiPanel.add(moodDropdown);
        uiPanel.add(playButton);

        add(uiPanel);
    }

    /**
     * Displays the playback loop UI dialog for the selected mood.
     *
     * @param mood Mood selected by the user.
     */
    private void showMoodPlayerDialog(String mood) {
        while (true) {
            List<Song> songs = moodRepository.getSongsForMood(mood);
            Song chosen = selectRandomSong(songs);
            SongPlayer.play(chosen.getFilePath());

            int choice = JOptionPane.showOptionDialog(
                this,
                "Now playing: " + chosen.getTitle() + "\nMood: " + mood,
                "Mood Music",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Another Song", "Change Mood", "Stop & Exit"},
                "Another Song"
            );

            SongPlayer.stop();

            if (choice == JOptionPane.YES_OPTION) {
                continue;
            } else if (choice == JOptionPane.NO_OPTION) {
                break;
            } else {
                System.exit(0);
            }
        }
    }

    /**
     * Selects a random song from a list.
     *
     * @param songs List of songs.
     * @return Randomly selected Song.
     */
    private Song selectRandomSong(List<Song> songs) {
        int index = ThreadLocalRandom.current().nextInt(songs.size());
        return songs.get(index);
    }
}

