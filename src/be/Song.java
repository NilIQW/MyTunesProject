package be;

public class Song {
<<<<<<< HEAD
    private int ID = -1;
    private String title;
=======
    private int id = -1;
    private String name;
>>>>>>> e5e3e6370da4e38a8c61da3f9c87ac183c29ea0a
    private String artist;
    private String filePath;

    public Song(String title, String artist, String genre, int time, String filePath) {
        this.title=title;
        this.artist=artist;
        this.filePath=filePath;
    }

    public Song (int id , String name, String artist, String filePath) {
        this.id= id;
        this.name= name;
        this.artist= artist;
        this.filePath= filePath;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
<<<<<<< HEAD
=======
    @Override
    public String toString (){
        return "Song=" +
                "id= " + id +
                "name= " + name +
                "artist= " + artist +
                "file path= " + filePath ;
    }

>>>>>>> e5e3e6370da4e38a8c61da3f9c87ac183c29ea0a
}
