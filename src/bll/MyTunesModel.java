package bll;

import be.Playlist;
import be.Song;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.MediaPlayer;

import java.sql.Connection;
import java.util.List;

public class MyTunesModel {
    private MediaPlayer mediaPlayer;
    private ObservableList<Song> songs;
    private ObservableList<Playlist> playlists;
    private final ObservableList<Song> songsOnPlaylist;
    private final ListProperty<Playlist> playlistsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
    //private final Connection connection;

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }
    public MyTunesModel(){
        //this.connection = connection;
        songs = FXCollections.observableArrayList();
        playlists = FXCollections.observableArrayList();
        songsOnPlaylist = FXCollections.observableArrayList();
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
        songsOnPlaylist.addAll(songsToAdd);
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

    public void removePlaylist(Playlist selectedPlaylist) {
        System.out.println("Attempting to remove playlist: " + selectedPlaylist.getName());
        playlists.removeIf(playlist -> {
            boolean shouldRemove = playlist.equals(selectedPlaylist);
            if (shouldRemove) {
                System.out.println("Removed playlist: " + playlist.getName());
            }
            return shouldRemove;
        });
    }
    public void updatePlaylist(Playlist playlist, String newName) {
        playlist.setName(newName);
    }


    public ObservableList<Song> getPlaylistSongs() {
        return songsOnPlaylist;
    }
}
