package gui;

import be.Playlist;
import be.Song;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.MediaPlayer;

import java.util.List;

public class MyTunesModel {
    private MediaPlayer mediaPlayer;

    private ObservableList<Song> songs;
    private ObservableList<Playlist> playlists;
    private final ListProperty<Playlist> playlistsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }
    public MyTunesModel(){
        songs = FXCollections.observableArrayList();
        playlists = FXCollections.observableArrayList();
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
    public void addSongsToThePlaylist(Playlist playlist, List<Song> songsToAdd) {
        playlist.addSongs((Song) songsToAdd);
        songs.addAll(songsToAdd);
    }
    public void createPlaylist(String playlistName) {
        Playlist newPlaylist = new Playlist(playlistName);
        playlists.add(newPlaylist);
        playlistsProperty.set(FXCollections.observableArrayList(playlists));
    }
    public ObservableList<Playlist> getPlaylists() {
        return playlists;
    }
    public ListProperty<Playlist> playlistsProperty() {
        return playlistsProperty;
    }
}
