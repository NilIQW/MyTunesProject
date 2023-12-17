package dal;

import be.Song;

import java.util.List;

public interface ISongDAO {
    Song getSong(int id);

    void addSong(Song song);

    void updateSong(Song song);

    void deleteSong(int id);

    List<Song> getAllSongs();
}