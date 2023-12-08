package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewPlaylistController implements Initializable {
    private Stage newSongWin;

    private void setNewSongWin(Stage stage){
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


    /*public static void display(){
        Stage newPlaylist = new Stage();
        newPlaylist.initModality(Modality.APPLICATION_MODAL);
        newPlaylist.setTitle("New/Edit Playlist");

        Button cancel = new Button("Cancel");
        cancel.setOnAction(event -> newPlaylist.close());

        VBox layout = new VBox(50);
        layout.getChildren().add(cancel);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        newPlaylist.setScene(scene);
        newPlaylist.showAndWait();
    }*/

}
