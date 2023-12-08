package gui.controller;

import be.Song;
import gui.MyTunesModel;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MyTunesController implements Initializable {


    @FXML
    private TableView<Song> songTableView;
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
    public TableView<Song> getSongTableView() {
        return songTableView;
    }
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
        TableColumn<Song, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Song, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Song, String> artistColumn = new TableColumn<>("Artist");
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));
        TableColumn<Song, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        TableColumn<Song, String> durationColumn = new TableColumn<>("Duration");
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        songTableView.getColumns().addAll(idColumn, titleColumn, artistColumn, genreColumn, durationColumn);

    }

    public void newPlaylist(ActionEvent actionEvent) {
        NewPlaylistController.display();
    }
    public void editPlaylist(ActionEvent actionEvent) {
        NewPlaylistController.display();
    }


    public void openNewSongWin(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/newSongWin.fxml"));
            Parent root = loader.load();

            NewSongWinController newSongWinController = loader.getController();
            newSongWinController.setSongTableView(songTableView);
            newSongWinController.setMyTunesModel(model);
            newSongWinController.setMyTunesController(this);
            newSongWinController.setNewSongWindow(new Stage());
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void playSelectedSong(){
        MyTunesController myTunesController = new MyTunesController();
        Song selectedSong = songTableView.getSelectionModel().getSelectedItem();
        if(selectedSong!=null && selectedSong.getFilePath() != null && !selectedSong.getFilePath().isEmpty()){
            String filePath = selectedSong.getFilePath();
            Media media = new Media(new File(filePath).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            model.setMediaPlayer(mediaPlayer);

            mediaPlayer.setVolume(volumeSlider.getValue());

            titleColumn.setText(selectedSong.getTitle());

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

    public void close(ActionEvent actionEvent) {

    }
    public void deleteSongs(){
        ObservableList<Song> selectedSongs = songTableView.getSelectionModel().getSelectedItems();
        if(!selectedSongs.isEmpty()){
            model.removeSongs(selectedSongs);
        }
    }

    public void DeleteSong(ActionEvent actionEvent) {
        deleteSongs();
        initializeSongTable();
    }

    public void DeletePSong(ActionEvent actionEvent) {

    }

    public void DeletePlaylist(ActionEvent actionEvent) {
    }
}
