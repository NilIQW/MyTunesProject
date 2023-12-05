package gui.controller;

import be.Song;
import gui.MyTunesModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NewSongWinController implements Initializable {
    @FXML
    private TextField titleTextfield;
    @FXML
    private TextField artistTextfield;
    @FXML
    private TextField timeTextfield;
    @FXML
    private ListView<Song> songListView;

    @FXML
    private ChoiceBox<String> genreChoicebox;
    private String filePath = "media";

    private MyTunesModel model;


    public void start(Stage newSongWindow){
        newSongWindow.setTitle("New/Edit Song");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateChoicebox();
    }
    public void chooseSongbtn(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose songs");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Music Files","*.mp3", "*.wav"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(songListView.getScene().getWindow());

        if(selectedFiles!=null){
            loadSongs(selectedFiles);
        }
    }
    private void loadSongs(List<File> songFiles) {

        for (File file : songFiles) {
            Song newSong = new Song();
            newSong.setFilePath(file.getAbsolutePath());
            model.addSong(newSong);
        }
        songListView.setItems(FXCollections.observableArrayList(model.getSongs()));

    }

    public void setSongListView(ListView<Song> songListView) {
        this.songListView = songListView;
    }
    public void saveSongInfo(){
        String title = titleTextfield.getText();
        String artist = artistTextfield.getText();
        String time = timeTextfield.getText();
        String genre = genreChoicebox.getValue();

        //String songInfo = String.format("%s - %s- %s - (%d sec)", title, artist, genre, time);
        Song newSong = new Song(title, artist, genre, filePath, time);
        model.addSong(newSong);
        titleTextfield.clear();
        artistTextfield.clear();
        timeTextfield.clear();

    }

    public void setMyTunesModel(MyTunesModel model){
        this.model=model;
    }

    public void populateChoicebox(){
        ObservableList<String> genres = FXCollections.observableArrayList("Pop", "Rock","Country","Hip Hop");
        genreChoicebox.setItems(genres);
        genreChoicebox.setValue("Funky Chicken dance?");
    }



}

