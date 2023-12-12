package gui.controller;

import be.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Filter {
    public static ObservableList<Song> filterSongs(ObservableList<Song> allSongs, String searchTerm) {
        ObservableList<Song> filteredSongs = FXCollections.observableArrayList();

        searchTerm = searchTerm.toLowerCase().trim();

        if (!searchTerm.isEmpty()) {
            for (Song song : allSongs) {
                if (songMatchesSearchTerm(song, searchTerm)){
                    filteredSongs.add(song);
                }
            }
        } else {
            return allSongs;
        }

        return filteredSongs;
    }

    private static boolean songMatchesSearchTerm(Song song, String searchTerm) {
        return song.getTitle().toLowerCase().trim().contains(searchTerm) ||
                song.getArtist().toLowerCase().trim().contains(searchTerm);
    }
}
