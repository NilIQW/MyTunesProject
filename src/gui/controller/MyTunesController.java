package gui.controller;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MyTunesController implements Initializable {
    @FXML
    public ListView<String> songListView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> songs = FXCollections.observableArrayList(
                "");
        MyTunesController myTunesController = new MyTunesController();
        songListView.setItems(songs);
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
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../view/newSongWin.fxml"));
        Parent root=loader.load();

        NewSongWinController newSongWinController = loader.getController();
        newSongWinController.setSongListView(songListView);
        Stage stage =new Stage();
        stage.setScene(new Scene(root));
        stage.show();



    }
}