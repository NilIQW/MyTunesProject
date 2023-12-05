package gui;

import gui.controller.MyTunesController;
import gui.controller.NewSongWinController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MyTunesApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MyTunesApplication.class.getResource("/gui/view/myTunes.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 600);
        stage.setTitle("MyTunes");
        stage.setScene(scene);
        stage.show();
        MyTunesController myTunesController = fxmlLoader.getController();
        MyTunesModel model = new MyTunesModel();
        myTunesController.setModel(model);

    }
    public static void main(String[] args) {launch();
    }
}