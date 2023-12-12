package be;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlist {
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name=name;
        this.songs=new ArrayList<>();
    }
    public void setName(Playlist newPlaylistName) {
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public List<Song> getSongs() {
        return songs;
    }

    public void addSongs(Song song){
        songs.add(song);
        song.addPlaylist(this);
    }
    public void removeSongs(Song song){
        song.removePlaylist(this);
    }
    @Override
    public String toString(){
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


}
