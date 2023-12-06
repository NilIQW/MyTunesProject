package be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Song {
    private int ID = -1;
    private String title;
    private String artist;
    private String filePath;

    public Song(String title, String artist, String genre, int time, String filePath) {
        this.title=title;
        this.artist=artist;
        this.filePath=filePath;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public StringProperty titleProperty() {
        return new SimpleStringProperty();
    }
    public StringProperty artistProperty() {
        return new SimpleStringProperty();
    }
    public StringProperty genreProperty() {
        return new SimpleStringProperty();
    }
}
