package dal;

import be.Song;

import java.util.List;

public interface ISongDAO {
    public Song getSong(int id);
    public void deleteSong(int id);
    public void updateSong(Song song);
    public void createSong(Song song);
    public List<Song> getAllSongs();
}