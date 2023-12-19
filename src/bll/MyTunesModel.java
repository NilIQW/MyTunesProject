package bll;

import be.Playlist;
import be.Song;
import dal.ConnectionManager;
import dal.ISongDAO;
import dal.SongDAO;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.MediaPlayer;

import java.sql.Connection;
import java.util.List;

public class MyTunesModel {
    private MediaPlayer mediaPlayer;
    private final ObservableList<Song> songs;
    private final ObservableList<Playlist> playlists;
    private final ObservableList<Song> songsOnPlaylist;
    private final ListProperty<Playlist> playlistsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final Connection connection;
    private final ISongDAO songDAO= new SongDAO(new ConnectionManager());
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public MyTunesModel(Connection connection){
        this.connection = connection;
        songs = FXCollections.observableArrayList();
        playlists = FXCollections.observableArrayList();
        songsOnPlaylist = FXCollections.observableArrayList();

        loadSongsFromDatabase();
    }

    private final ListProperty<Song> songsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());

    public ListProperty<Song> songsProperty() {
        return songsProperty;
    }

    private void loadSongsFromDatabase() {
        try {
            List<Song> songsFromDatabase = songDAO.getAllSongs();
            songs.addAll(songsFromDatabase);

            // Update the property, triggering any listeners
            songsProperty.set(FXCollections.observableArrayList(songs));
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }



    public ObservableList<Song> getSongs(){
        return songs;
    }
    public void addSongs(Song song) {
        songs.add(song);
    }
    public void removeSongs(List<Song> songsToRemove){
        songs.removeAll(songsToRemove);

    }
    public void createPlaylist(Playlist newPlaylist) {
        playlists.add(newPlaylist);
        playlistsProperty.set(FXCollections.observableArrayList(playlists));
    }
    public ObservableList<Playlist> getPlaylists() {
        return playlists;
    }
    public ListProperty<Playlist> playlistsProperty() {
        return playlistsProperty;
    }

    public void removePlaylist(Playlist selectedPlaylist) {
        playlists.remove(selectedPlaylist);
    }

    public ObservableList<Song> getPlaylistSongs() {
        return songsOnPlaylist;
    }


}
