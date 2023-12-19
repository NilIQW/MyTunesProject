package dal;

import be.Playlist;
import be.Song;

import java.util.List;

public interface IPlaylistDAO {

    Playlist getPlaylist(int id);

    void addPlaylist(Playlist playlist);

    void updatePlaylist(Playlist playlist);

    void deletePlaylist(int id);

    List<Playlist> getAllPlaylists();

}
