package bll;

import be.Playlist;
import be.Song;
import dal.*;
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
    private final ListProperty<Playlist> playlistsProperty;
    private final Connection connection;
    private final ISongDAO songDAO= new SongDAO(new ConnectionManager());
    private final IPlaylistDAO playlistDAO = new PlaylistDAO(new ConnectionManager());
    private PlaylistManager myPlaylistManager;
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
        playlistsProperty = new SimpleListProperty<>(playlists);
        songsOnPlaylist = FXCollections.observableArrayList();
        myPlaylistManager = new PlaylistManager(new PlaylistDAO(new ConnectionManager()));

        loadSongsFromDatabase();
        loadPlaylistsFromDatabase();
    }
    private void loadPlaylistsFromDatabase() {
        try {
            List<Playlist> playlistsFromDatabase = playlistDAO.getAllPlaylists();

            // Clear the existing playlists and addAll from the database
            playlists.clear();
            playlists.addAll(playlistsFromDatabase);

            // Update the property, triggering any listeners
            playlistsProperty.set(FXCollections.observableArrayList(playlists));

        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }

    private final ListProperty<Song> songsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());

    public ListProperty<Song> songsProperty() {
        return songsProperty;
    }

    private void loadSongsFromDatabase() {
        try {
            List<Song> songsFromDatabase = songDAO.getAllSongs();
            songs.addAll(songsFromDatabase);

            songsProperty.set(FXCollections.observableArrayList(songs));
        } catch (Exception e) {
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
        playlistsProperty.add(newPlaylist);

        myPlaylistManager.addPlaylist(newPlaylist);
    }

    public ObservableList<Playlist> getPlaylists() {
        return playlists;
    }
    public ListProperty<Playlist> playlistsProperty() {
        return playlistsProperty;
    }

    public void removePlaylist(Playlist selectedPlaylist) {
        playlistsProperty.remove(selectedPlaylist);
    }


    public ObservableList<Song> getPlaylistSongs() {
        return songsOnPlaylist;
    }

    public void addSongToPlaylist(Playlist playlist, Song song) {
        playlist.addSongs(song);
    }


}
