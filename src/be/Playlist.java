package be;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlist {
    private int id;
    private String name;
    private List<Song> songs;

    public Playlist(int id, String name) {
        this.id=id;
        this.name = name;
        this.songs = new ArrayList<>();
    }
    public Playlist(String name) {
        this.id = -1; //
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSongs(Song song) {
        songs.add(song);
        song.addPlaylist(this);
    }

    public void removeSongs(Song song) {
        songs.remove(song);
        song.removePlaylist(this);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return Objects.equals(name, playlist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void removeSong(Song song) {
        songs.remove(song);

    }
}
