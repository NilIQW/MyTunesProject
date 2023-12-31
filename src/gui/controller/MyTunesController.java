package gui.controller;

import be.Playlist;
import be.Song;
import bll.MyTunesModel;
import bll.PlaylistManager;
import bll.SongManager;
import dal.ConnectionManager;
import dal.ISongDAO;
import dal.PlaylistDAO;
import dal.SongDAO;
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
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class MyTunesController implements Initializable {
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ListView<Song> playlistSongsView;
    @FXML
    private TextField filterTextfield;
    @FXML
    private Button filterButton;
    @FXML
    private ListView<Playlist> playlistView;
    @FXML
    private TableView<Song> songTableView;
    @FXML
    private TableColumn<Song, String> durationColumn;
    @FXML
    private TableColumn<Song, String> idColumn;
    @FXML
    private TableColumn<Song, String> titleColumn;
    @FXML
    private TableColumn<Song, String> artistColumn;
    @FXML
    private TableColumn<Song, String> genreColumn;
    @FXML
    private Label titleLabel;
    @FXML
    private Slider volumeSlider;
    private MyTunesModel model;
    private MediaPlayer mediaPlayer;
    private ObservableList<Song> filteredSongs;
    private SongManager mySongManager;
    private PlaylistManager myPlaylistManager;
    public MyTunesController(){
        this.mySongManager = new SongManager(new SongDAO(new ConnectionManager()) {
        });
        this.myPlaylistManager = new PlaylistManager(new PlaylistDAO(new ConnectionManager()));
    }
    public void setModel(MyTunesModel model) {
        this.model = model;
        playlistView.setItems(model.playlistsProperty());
        playlistSongsView.setItems(model.getPlaylistSongs());
        songTableView.itemsProperty().bind(model.songsProperty());

    }
    public void setSongManager(SongManager mySongManager) {
        this.mySongManager = mySongManager;
    }

    public void setSongTableView(TableView<Song> songTableView) {
        this.songTableView = songTableView;
    }
    public TableView<Song> getSongTableView() {
        return songTableView;
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
    @FXML
    public void editPlaylistName(ActionEvent actionEvent) {
        Playlist selectedPlaylist = playlistView.getSelectionModel().getSelectedItem();
        if (selectedPlaylist != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/newPlaylist.fxml"));
                Parent root = loader.load();

                NewPlaylistController newPlaylistController = loader.getController();
                newPlaylistController.setModel(model);
                newPlaylistController.setNewSongWin(new Stage());
                newPlaylistController.setPlaylistToEdit(selectedPlaylist);

                Stage stage = new Stage();
                stage.setTitle("Edit Playlist");
                stage.setScene(new Scene(root));
                stage.showAndWait();

                if (newPlaylistController.isPlaylistUpdated()) {

                    playlistView.getItems().clear();
                    playlistView.getItems().addAll(model.getPlaylists());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void openNewSongWin(ActionEvent actionEvent) throws IOException {
        try {FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/newSongWin.fxml"));
            Parent root = loader.load();

            NewSongWinController newSongWinController = loader.getController();
            newSongWinController.setMyTunesController(this);
            newSongWinController.setMySongManager(mySongManager);
            newSongWinController.setMyTunesModel(model);
            newSongWinController.setSongTableView(songTableView);
            newSongWinController.setNewSongWindow(new Stage());

            Stage stage = new Stage();

            Stage mainStage = (Stage) songTableView.getScene().getWindow();
            stage.initOwner(mainStage);

            stage.setTitle("New Song");
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
            newSongWinController.setMySongManager(mySongManager);
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
    public void play(ActionEvent actionEvent) {
        playSelectedSong();
    }
    public void playSelectedSong() {
        Song selectedSong = null;
        // Check if a song is selected in the playlistSongsView
        if (!playlistSongsView.getSelectionModel().getSelectedItems().isEmpty()) {
            selectedSong = playlistSongsView.getSelectionModel().getSelectedItem();
        }
        // If no song is selected in the playlistSongsView, check the songTableView
        else if (!songTableView.getSelectionModel().getSelectedItems().isEmpty()) {
            selectedSong = songTableView.getSelectionModel().getSelectedItem();
        }
        if (selectedSong != null && selectedSong.getFilePath() != null && !selectedSong.getFilePath().isEmpty()) {
            String filePath = selectedSong.getFilePath();
            Media media = new Media(new File(filePath).toURI().toString());

            stopCurrentPlayback();

            mediaPlayer = new MediaPlayer(media);
            model.setMediaPlayer(mediaPlayer);

            titleLabel.setText(selectedSong.getTitle());

            mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                Duration currentTime = mediaPlayer.getCurrentTime();
                Duration totalDuration = media.getDuration();

                // Calculate progress as a value between 0 and 1
                double progress = currentTime.toMillis() / totalDuration.toMillis();

                // Update the progress bar
                progressBar.setProgress(progress);
            });
            mediaPlayer.play();
        }
    }
    private void stopCurrentPlayback() {
        if (mediaPlayer != null) {
            progressBar.progressProperty().unbind();
            mediaPlayer.stop();
        }
    }

    public void pauseBtn(ActionEvent actionEvent) {
        MediaPlayer mediaPlayer = model.getMediaPlayer();
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }
    private void initializeSongTable() {
        ObservableList<Song> songs = FXCollections.observableArrayList();
        songTableView.setItems(songs);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeSongTable();
        filteredSongs = FXCollections.observableArrayList();

        volumeSlider.setValue(50); // Set an initial volume value
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            adjustVolume(newValue.doubleValue() / 100.0); // Convert to a value between 0 and 1
        });
        playlistView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Update the playlistSongsView based on the selected playlist
            updatePlaylistSongsView();
        });
    }
    public void updatePlaylistSongsView() {
        Playlist selectedPlaylist = playlistView.getSelectionModel().getSelectedItem();
        if (selectedPlaylist != null) {
            List<Song> songsInPlaylist = selectedPlaylist.getSongs();
            playlistSongsView.setItems(FXCollections.observableArrayList(songsInPlaylist));
        } else {
            // Clear the playlistSongsView if no playlist is selected
            playlistSongsView.setItems(FXCollections.emptyObservableList());
        }
    }

    private void adjustVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }
    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
    public void deleteSong(ActionEvent actionEvent) {
        ObservableList<Song> selectedSongs = songTableView.getSelectionModel().getSelectedItems();

        if (!selectedSongs.isEmpty()) {
            for (Song song : selectedSongs) {
                mySongManager.deleteSong(song.getId());
                model.removeSongs(selectedSongs);
            }
            // Use setAll to update the items of the TableView
            songTableView.getItems().setAll(model.getSongs());
        }
    }
    public void deletePlaylist(ActionEvent actionEvent) {
        Playlist selectedPlaylist = playlistView.getSelectionModel().getSelectedItem();
        if (selectedPlaylist != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Playlist");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the playlist '" + selectedPlaylist.getName() + "'?");

            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.UNDECORATED);

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    model.removePlaylist(selectedPlaylist);
                    myPlaylistManager.deletePlaylist(selectedPlaylist.getId());

                    playlistView.getItems().clear();
                    playlistView.getItems().addAll(model.getPlaylists());
                }
            });
        }
    }
    public void addSongsToPlaylist(ActionEvent actionEvent) {
        Playlist selectedPlaylist = playlistView.getSelectionModel().getSelectedItem();
        Song selectedSong = songTableView.getSelectionModel().getSelectedItem();

        if (selectedPlaylist != null && selectedSong != null) {
            // Add the selected song to the playlist
            model.addSongToPlaylist(selectedPlaylist, selectedSong);

            // Update the playlistSongsView with the updated list of songs
            List<Song> songsInPlaylist = selectedPlaylist.getSongs();
            playlistSongsView.getItems().setAll(songsInPlaylist);
        }
    }
    public void filterButton(ActionEvent actionEvent) {
        String searchTerm = filterTextfield.getText().trim().toLowerCase();
        if (searchTerm.isEmpty()) {
            songTableView.setItems(model.getSongs());
        } else {
            filteredSongs = model.getSongs().filtered(song ->
                    song.getTitle().toLowerCase().contains(searchTerm) ||
                            song.getArtist().toLowerCase().contains(searchTerm)
            );
            songTableView.setItems(filteredSongs);
        }
    }
    public void DeletePSong(ActionEvent actionEvent) {
        Playlist selectedPlaylist = playlistView.getSelectionModel().getSelectedItem();
        Song selectedSong = playlistSongsView.getSelectionModel().getSelectedItem();

        if (selectedPlaylist != null && selectedSong != null) {
            // Remove the selected song from the playlist
            selectedPlaylist.removeSong(selectedSong);
            mySongManager.deleteSong(selectedSong.getId());

            // Update the playlistSongsView with the updated list of songs
            playlistSongsView.setItems(FXCollections.observableArrayList(selectedPlaylist.getSongs()));
        }
    }
    public void playNext(ActionEvent actionEvent) {
        int selectedIndex = songTableView.getSelectionModel().getSelectedIndex();

        // Check if there is a next song
        if (selectedIndex < songTableView.getItems().size() - 1) {
            // Increment the index to get the next song
            int nextIndex = selectedIndex + 1;

            // Select the next song in the table view
            songTableView.getSelectionModel().select(nextIndex);

            // Play the selected song
            playSelectedSong();
        }
    }
    public void playPrevious(ActionEvent actionEvent) {
        int selectedIndex = songTableView.getSelectionModel().getSelectedIndex();

        if(selectedIndex > 0){
            int prevIndex = selectedIndex - 1;

            songTableView.getSelectionModel().select(prevIndex);

            playSelectedSong();
        }
    }

    public void moveUp(ActionEvent actionEvent) {
        Song selectedSong = playlistSongsView.getSelectionModel().getSelectedItem();
        ObservableList<Song> songsInPlaylist = playlistSongsView.getItems();

        int selectedIndex = songsInPlaylist.indexOf(selectedSong);

        if (selectedIndex > 0) {
            // Swap the selected song with the one above it
            Song aboveSong = songsInPlaylist.get(selectedIndex - 1);
            songsInPlaylist.set(selectedIndex - 1, selectedSong);
            songsInPlaylist.set(selectedIndex, aboveSong);

            // Select the moved song in the playlistSongsView
            playlistSongsView.getSelectionModel().select(selectedIndex - 1);
        }
    }

    public void moveDown(ActionEvent actionEvent) {
        Song selectedSong = playlistSongsView.getSelectionModel().getSelectedItem();
        ObservableList<Song> songsInPlaylist = playlistSongsView.getItems();

        int selectedIndex = songsInPlaylist.indexOf(selectedSong);

        if (selectedIndex < songsInPlaylist.size() - 1) {
            Song belowSong = songsInPlaylist.get(selectedIndex + 1);
            songsInPlaylist.set(selectedIndex + 1, selectedSong);
            songsInPlaylist.set(selectedIndex, belowSong);

            playlistSongsView.getSelectionModel().select(selectedIndex + 1);
        }


    }
}
