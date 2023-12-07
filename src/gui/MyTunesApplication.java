package gui;

import gui.controller.MyTunesController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyTunesApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/myTunes.fxml"));
        Parent root = loader.load();

        MyTunesController myTunesController = loader.getController();
        myTunesController.setModel(new MyTunesModel());
        myTunesController.setSongTableView(myTunesController.songTableView);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My Tunes");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
