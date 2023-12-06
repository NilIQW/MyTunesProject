package gui.controller;

import be.Song;
import gui.MyTunesModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableView<Song> songTableView;
    private MyTunesController myTunesController;

    @FXML
    private ChoiceBox<String> genreChoicebox;
    private String filePath = "media";
    private MyTunesModel model;
    private Stage newSongWindow;


    public void setMyTunesController(MyTunesController myTunesController) {
        this.myTunesController = myTunesController;
    }

    public void start(Stage newSongWindow){
        this.newSongWindow=newSongWindow;
        newSongWindow.setTitle("New/Edit Song");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateChoicebox();
    }
    public void chooseSongbtn(ActionEvent actionEvent) {
        if(songTableView !=null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose songs");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Music Files", "*.mp3", "*.wav"));
            List<File> selectedFiles = fileChooser.showOpenMultipleDialog(songTableView.getScene().getWindow());

            if (selectedFiles != null) {
                loadSongs(selectedFiles);
            }
            MyTunesController myTunesController = new MyTunesController();
            if (myTunesController != null) {
                myTunesController.playSelectedSong();//problem??
            }
        }
    }
    private void loadSongs(List<File> songFiles) {
        if(model != null) {
            for (File file : songFiles) {
                Song newSong = new Song(file.getName(), "", "", 0, "");
                newSong.setFilePath(file.getAbsolutePath());
                model.addSong(newSong);
            }
            ObservableList<Song> updatedSongs =FXCollections.observableArrayList(model.getSongs());
            songTableView.setItems(updatedSongs);
        }
    }

    public void setSongTableView(TableView<Song> songTableView) {
        this.songTableView = songTableView;
    }
    public void saveSongInfo(){
        String title = titleTextfield.getText();
        String artist = artistTextfield.getText();
        int time = Integer.parseInt(timeTextfield.getText());
        String genre = genreChoicebox.getValue();
        Song newSong = new Song(title, artist, genre, time, filePath);
        model.addSong(newSong);
        titleTextfield.clear();
        artistTextfield.clear();
        timeTextfield.clear();
        newSongWindow.close();

    }

    public void setMyTunesModel(MyTunesModel model){
        this.model=model;
    }

    public void populateChoicebox(){
        ObservableList<String> genres = FXCollections.observableArrayList("Pop", "Rock","Country","Hip Hop");
        genreChoicebox.setItems(genres);
        genreChoicebox.setValue("Pop");
    }


    public void done(ActionEvent actionEvent) {

    }
}

