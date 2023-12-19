package be;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public Song(int id) {
        this.id = id;
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
    public String getDuration() {
        return duration;
    }
    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }
    public void removePlaylist(Playlist playlist){
        playlists.remove(playlist);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(title, song.title) && Objects.equals(artist, song.artist);
    }
    @Override
    public String toString() {
        return getTitle();
    }
}