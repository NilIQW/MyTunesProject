package be;

public class Song {
    private int id;
    private String title;
    private String artist;
    private String genre;
    private String filePath;
<<<<<<< HEAD
    private String time;

    public Song(int id, String title, String artist, String genre, String filePath, String time) {
=======
    private String duration;

    /*public Song(int id, String title, String artist, String genre, String filePath, String duration) {
>>>>>>> revert(2hrs-ago)
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.filePath = filePath;
<<<<<<< HEAD
        this.time = time;
    }

    public Song(String title, String artist, String genre, String filePath, String time) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.filePath = filePath;
        this.time = time;
    }

    public Song() {

    }

    public int getId() {
        return id;
    }

=======
        this.duration = duration;
    }*/

    public Song(String title, String artist, String genre, String filePath, String duration) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.filePath = filePath;
        this.duration = duration;
    }

    public Song() {

    }

    public int getId() {
        return id;
    }

>>>>>>> revert(2hrs-ago)
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

<<<<<<< HEAD
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
=======
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
>>>>>>> revert(2hrs-ago)
