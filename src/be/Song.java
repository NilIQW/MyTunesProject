package be;

import java.util.ArrayList;
import java.util.List;

public class Song {
    private int id;
    private String title;
    private String artist;
    private String genre;
    private String filePath;
    private String duration;
    private List<Playlist> playlists;

    public Song(){
        this.id= -1;
        this.playlists=new ArrayList<>();
    }

    public Song(String title, String artist, String genre, String filePath, String duration) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.filePath = filePath;
        this.duration = duration;
        this.playlists=new ArrayList<>();
    }

    public Song(int id, String title, String artist, String genre, String filePath, String duration) {
        this.id=id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.filePath = filePath;
        this.duration = duration;
        this.playlists=new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }
    public void removePlaylist(Playlist playlist){
        playlists.remove(playlist);
    }

    @Override
    public String toString() {
        return title;
    }
}