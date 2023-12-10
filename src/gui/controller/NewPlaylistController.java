package gui.controller;

import gui.MyTunesModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewPlaylistController implements Initializable {
    private Stage newSongWin;
    @FXML
    private TextField playlistTextfield;

    private MyTunesModel model;

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

        if(!playlistName.isEmpty()){
            model.createPlaylist(playlistName);
        }
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


}
