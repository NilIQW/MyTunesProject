package bll;

import be.Playlist;
import dal.IPlaylistDAO;
import dal.PlaylistDAO;

import java.util.List;

public class PlaylistManager {
    private final IPlaylistDAO playlistDAO;

    public PlaylistManager(IPlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }
    public void addPlaylist(Playlist playlist){
        playlistDAO.addPlaylist(playlist);
    }
    public void updatePlaylist(Playlist playlist){
        playlistDAO.updatePlaylist(playlist);
    }
    public void deletePlaylist(int id){
        playlistDAO.deletePlaylist(id);
    }
    public List<Playlist> getAllPlaylist(){
        return playlistDAO.getAllPlaylists();
    }
}
