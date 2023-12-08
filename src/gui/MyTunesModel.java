package gui;

import be.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.MediaPlayer;

import java.util.List;

public class MyTunesModel {
    private MediaPlayer mediaPlayer;

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    private ObservableList<Song> songs;

    public MyTunesModel(){

        songs = FXCollections.observableArrayList();
    }
    public ObservableList<Song> getSongs(){

        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSongs(List<Song> songsToRemove){
        songs.removeAll(songsToRemove);

    }
}
