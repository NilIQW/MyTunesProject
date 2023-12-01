package gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
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
    private ListView<String> songListView;

    @FXML
    private ChoiceBox<String> genreChoicebox;

    public void start(Stage newSongWindow){
        newSongWindow.setTitle("New/Edit Song");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> genres = FXCollections.observableArrayList("Pop", "Rock","Country","Hip Hop");
        genreChoicebox.setItems(genres);

        genreChoicebox.setValue("Funky Chicken dance?");
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
        ObservableList<String> songs = FXCollections.observableArrayList();
        for (File file : songFiles) {
            songs.add(file.getName());
        }
        songListView.setItems(songs);
    }

    public void setSongListView(ListView<String> songListView) {
        this.songListView = songListView;
    }
    public void saveSongInfo(){
        String title = titleTextfield.getText();
        String artist = artistTextfield.getText();
        int time = Integer.parseInt(timeTextfield.getText());
        String genre = genreChoicebox.getValue();

        String songInfo = String.format("%s - %s- %s - (%d sec)", title, artist, genre, time);
        songListView.getItems().add(songInfo);

        titleTextfield.clear();
        artistTextfield.clear();
        timeTextfield.clear();

    }
}

