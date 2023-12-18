package bll;

import be.Song;
import dal.ISongDAO;

import java.util.List;

public class SongManager {
    private final ISongDAO songDAO;

    public SongManager(ISongDAO songDAO) {

        this.songDAO = songDAO;
    }
    public void addSong(Song song){
        songDAO.addSong(song);
    }
    public void updateSong(Song song){
        songDAO.updateSong(song);
    }
    public void deleteSong(int id){

        songDAO.deleteSong(id);

    }
    public Song getSong(int id){
        return songDAO.getSong(id);
    }
    public List<Song> getAllSongs(){
        return songDAO.getAllSongs();
    }

}
