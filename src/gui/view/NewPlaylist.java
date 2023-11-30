package gui.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewPlaylist {

    public static void display(){
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
    }

}
