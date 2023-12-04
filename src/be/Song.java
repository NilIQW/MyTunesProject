package be;

public class Song {
    private int id = -1;
    private String name;
    private String artist;
    private String filePath;


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
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Override
    public String toString (){
        return "Song=" +
                "id= " + id +
                "name= " + name +
                "artist= " + artist +
                "file path= " + filePath ;
    }

}
