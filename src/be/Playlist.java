package be;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name=name;
        this.songs=new ArrayList<>();
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
}
