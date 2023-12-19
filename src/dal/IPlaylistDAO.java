package dal;

import be.Playlist;

import java.util.List;

public interface IPlaylistDAO {
    Playlist getPlaylist(int id);

    void addPlaylist(Playlist playlist);

    void updatePlaylist(Playlist playlist);

    void deletePlaylist(int id);

    List<Playlist> getAllPlaylists();
}
