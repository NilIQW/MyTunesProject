package gui.controller;

import be.Song;
import gui.MyTunesModel;
import gui.view.NewPlaylist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MyTunesController implements Initializable {
    @FXML
    public TableView<Song> songTableView;
    @FXML
    public TableColumn<Song, String> durationColumn;
    @FXML
    public TableColumn<Song, String> idColumn;
    @FXML
    public TableColumn<Song, String> titleColumn;
    @FXML
    public TableColumn<Song, String> artistColumn;
    @FXML
    public TableColumn<Song, String> genreColumn;
    private MyTunesModel model;
    @FXML
    private Slider volumeSlider;
    private MediaPlayer mediaPlayer;

    public void setModel(MyTunesModel model){
        this.model = model;
    }



    private void setVolume() {
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue();
            if(mediaPlayer !=null) {
            mediaPlayer.setVolume(volume);
            }
        });
    }


    private void setupTableColumns() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        artistColumn.setCellValueFactory(cellData -> cellData.getValue().artistProperty());
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
    }

    public void newPlaylist(ActionEvent actionEvent) {
        NewPlaylist.display();
    }
    public void editPlaylist(ActionEvent actionEvent) {
        NewPlaylist.display();
    }


    public void openNewSong(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/newSongWin.fxml"));
            Parent root = loader.load();
            NewSongWinController newSongWinController = loader.getController();
            newSongWinController.setSongTableView(songTableView);
            newSongWinController.setMyTunesModel(model);
            newSongWinController.setMyTunesController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void playSelectedSong(){
        Song selectedSong = songTableView.getSelectionModel().getSelectedItem();
        if(selectedSong!=null && selectedSong.getFilePath() != null && !selectedSong.getFilePath().isEmpty()){
            String filePath = selectedSong.getFilePath();
            Media media = new Media(new File(filePath).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            model.setMediaPlayer(mediaPlayer);

            mediaPlayer.setVolume(volumeSlider.getValue());

            titleColumn.setText(selectedSong.getName());

            mediaPlayer.play();

        }
    }
    public void pausePlayback() {
        MediaPlayer mediaPlayer = model.getMediaPlayer();
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void play(ActionEvent actionEvent) {
        playSelectedSong();
    }

    public void pauseBtn(ActionEvent actionEvent) {
        pausePlayback();
    }
    public void setSongTableView(TableView<Song> songTableView) {
        this.songTableView = songTableView;


    }
    private void initializeSongTable(){
        ObservableList<Song> songs = FXCollections.observableArrayList();
        songTableView.setItems(songs);
        if(model != null){
            songs.addAll(model.getSongs());
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTableColumns();
        initializeSongTable();

        if(volumeSlider != null) {
            setVolume();
        }
    }
}
