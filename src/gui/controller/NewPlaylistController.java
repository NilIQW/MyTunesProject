package gui.controller;

import be.Playlist;
import bll.MyTunesModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewPlaylistController implements Initializable {
    private Stage newSongWin;
    @FXML
    private TextField playlistTextfield;
    private MyTunesModel model;
    @FXML
    private Button saveBtn;
    private Playlist playlistToEdit;
    @FXML
    private ListView<Playlist> playlistView;
    private Playlist existingPlaylist;
    private boolean playlistUpdated;

    public void setPlaylistToEdit(Playlist playlistToEdit) {
        this.playlistToEdit = playlistToEdit;
        if (playlistToEdit != null) {
            // If editing an existing playlist, set the current name in the text field
            playlistTextfield.setText(playlistToEdit.getName());
        }
    }
    public boolean isPlaylistUpdated(){
        return playlistUpdated;
    }


    public void setModel(MyTunesModel model) {
        this.model = model;
    }

    public void setNewSongWin(Stage stage){
        this.newSongWin=stage;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void cancelBtn(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();

        Stage stage = (Stage) source.getScene().getWindow();

        stage.close();
    }

    public void savePlaylist(ActionEvent actionEvent) {
        String playlistName = playlistTextfield.getText();

        if (!playlistName.isEmpty()) {
            if (playlistToEdit != null) {
                // Editing an existing playlist
                playlistToEdit.setName(playlistName);
                playlistUpdated = true;
            } else {
                // Creating a new playlist
                model.createPlaylist(playlistName);
            }
        }

        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
