package gui.controller;

import be.Playlist;
import be.Song;
import bll.MyTunesModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class MyTunesController implements Initializable {
    @FXML
    private TextField filterTextfield;
    @FXML
    private Button filterButton;
    @FXML
    private ListView<Playlist> playlistView;
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
    private ObservableList<Song> allSongs;
    private ObservableList<Song> filteredSongs;

    public TableView<Song> getSongTableView() {
        return songTableView;
    }

    public void setModel(MyTunesModel model) {
        this.model = model;
        playlistView.setItems(model.playlistsProperty());
        initializeSearch();
    }

    private void setVolume() {
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue();
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(volume);
            }
        });
    }

    private void setupTableColumns() {
        idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        artistColumn = new TableColumn<>("Artist");
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));

        genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        durationColumn = new TableColumn<>("Duration");
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        songTableView.getColumns().addAll(idColumn, titleColumn, artistColumn, genreColumn, durationColumn);
    }

    public void newPlaylist(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/newPlaylist.fxml"));
        Parent root = loader.load();
        NewPlaylistController newPlaylistController = loader.getController();
        newPlaylistController.setModel(model);

        Stage stage = new Stage();
        stage.setTitle("New/Edit Playlist");

        stage.setScene(new Scene(root));
        stage.show();
    }

    public void editPlaylist(ActionEvent actionEvent) {

    }

    public void openNewSongWin(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/newSongWin.fxml"));
            Parent root = loader.load();

            NewSongWinController newSongWinController = loader.getController();
            newSongWinController.setMyTunesModel(model);
            newSongWinController.setSongTableView(songTableView);

            newSongWinController.setMyTunesController(this);
            newSongWinController.setNewSongWindow(new Stage());

            Stage stage = new Stage();

            Stage mainStage = (Stage) songTableView.getScene().getWindow();
            stage.initOwner(mainStage);

            stage.setTitle("New/Edit Song");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editBtn(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/newSongWin.fxml"));
            Parent root = loader.load();

            NewSongWinController newSongWinController = loader.getController();
            newSongWinController.setMyTunesModel(model);
            newSongWinController.setSongTableView(songTableView);


            Song selectedSong = songTableView.getSelectionModel().getSelectedItem();
            newSongWinController.setEditedSong(selectedSong);

            newSongWinController.setMyTunesController(this);
            newSongWinController.setNewSongWindow(new Stage());

            Stage stage = new Stage();

            Stage mainStage = (Stage) songTableView.getScene().getWindow();
            stage.initOwner(mainStage);

            stage.setTitle("Edit Song");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void playSelectedSong() {
        Song selectedSong = songTableView.getSelectionModel().getSelectedItem();
        if (selectedSong != null && selectedSong.getFilePath() != null && !selectedSong.getFilePath().isEmpty()) {
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

    private void initializeSongTable() {
        ObservableList<Song> songs = FXCollections.observableArrayList();
        songTableView.setItems(songs);
        if (model != null) {
            songs.addAll(model.getSongs());
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTableColumns();
        initializeSongTable();
        setupVolume();
        filteredSongs = FXCollections.observableArrayList();
    }




    public void setupVolume() {
        if (volumeSlider != null) {
            setVolume();
        }
    }

    public void initializeSearch() {
        if (model != null) {
            allSongs = FXCollections.observableArrayList(model.getSongs());
            filteredSongs = FXCollections.observableArrayList(allSongs);
            songTableView.setItems(filteredSongs);
        }
    }
    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void deleteSongs() {
        ObservableList<Song> selectedSongs = songTableView.getSelectionModel().getSelectedItems();
        if (!selectedSongs.isEmpty()) {
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


    public void addSongsToPlaylist(ActionEvent actionEvent) {

    }

    public void filterTextfield(ActionEvent actionEvent) {
    }

    public void filterButton(ActionEvent actionEvent) {
        if (allSongs == null) {
            return;
        }
        String filterText = filterTextfield.getText().toLowerCase().trim();
        try {
            List<Song> filteredList = Filter.filterSongs(allSongs, filterText);

            if (filteredList != null) {
                filteredSongs.setAll(filteredList);
                songTableView.setItems(filteredSongs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
