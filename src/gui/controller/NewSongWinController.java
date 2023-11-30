package gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
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
    public ListView<String> songListView;

    public void start(Stage newSongWindow){
        newSongWindow.setTitle("New/Edit Song");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
}

