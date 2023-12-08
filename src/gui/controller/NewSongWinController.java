package gui.controller;

import be.Song;
import gui.MyTunesModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private TableView<Song> songTableView;
    @FXML
    private ChoiceBox<String> genreChoicebox;
    private MyTunesModel model;
    private Stage newSongWindow;
    private MyTunesController myTunesController;
    public void setMyTunesController(MyTunesController myTunesController) {
        this.myTunesController = myTunesController;
    }
    public void setNewSongWindow(Stage newSongWindow){
        this.newSongWindow=newSongWindow;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chooseSongbtn(new ActionEvent());
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

        }
    }
    private void loadSongs(List<File> songFiles) {
        if(model != null) {
            for (File file : songFiles) {
                String filePath = file.getAbsolutePath();

                Media media = new Media(new File(filePath).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);


                mediaPlayer.setOnReady(() -> {
                    String duration = formatDuration(media.getDuration());
                    Song newSong = new Song(file.getName(), artistTextfield.getText(), genreChoicebox.getValue(), filePath, duration);
                    model.addSong(newSong);

                    ObservableList<Song> updatedSongs = FXCollections.observableArrayList(model.getSongs());
                    songTableView.setItems(updatedSongs);

                    mediaPlayer.setOnEndOfMedia(mediaPlayer::dispose);
                });

            }
                

            }

<<<<<<< HEAD
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
=======
        }
>>>>>>> revert(2hrs-ago)

    private String formatDuration(Duration duration){
        long minutes = (long) duration.toMinutes();
        long seconds = (long) (duration.toSeconds() % 60);
        return String.format("%02d:%02d", minutes, seconds);
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

        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


    public void setSongTableView(TableView<Song> songTableView) {
        this.songTableView = songTableView;
    }


}

