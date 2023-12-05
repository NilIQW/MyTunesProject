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
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MyTunesController implements Initializable {
    @FXML
    public ListView<Song> songListView;

    private MyTunesModel model;

    public void setModel(MyTunesModel model){
        this.model = model;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Song> songs = FXCollections.observableArrayList();
        songListView.setItems(songs);

        if (model != null) {
            songs.addAll(model.getSongs());
        }
    }
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My Tunes");
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
            newSongWinController.setSongListView(songListView);
            newSongWinController.setMyTunesModel(model);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void playSelectedSong(){
        Song selectedSong = songListView.getSelectionModel().getSelectedItem();
        if(selectedSong!=null && selectedSong.getFilePath() != null && !selectedSong.getFilePath().isEmpty()){
            String filePath = selectedSong.getFilePath();
            Media media = new Media(new File(filePath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            model.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
        }
    }
    public void stopPlayback() {
        MediaPlayer mediaPlayer = model.getMediaPlayer();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
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
}
