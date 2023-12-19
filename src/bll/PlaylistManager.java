package bll;

import be.Playlist;
import be.Song;
import dal.IPlaylistDAO;
import dal.ISongDAO;
import dal.PlaylistDAO;
import dal.SongDAO;

import java.util.Collections;
import java.util.List;

public class PlaylistManager {
    private IPlaylistDAO playlistDAO;
    public void setPlaylistDAO(IPlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }
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

}
