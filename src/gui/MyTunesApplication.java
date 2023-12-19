package gui;

import be.Song;
import bll.MyTunesModel;
import bll.SongManager;
import dal.ConnectionManager;
import dal.ISongDAO;
import dal.SongDAO;
import gui.controller.MyTunesController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.List;

public class MyTunesApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/myTunes.fxml"));
        Parent root = loader.load();
        MyTunesController myTunesController = loader.getController();

        myTunesController.setModel(new MyTunesModel(connection));
        myTunesController.setSongTableView(myTunesController.getSongTableView());

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My Tunes");
        primaryStage.show();
        ISongDAO songDAO = new SongDAO(connectionManager);

        SongManager songManager = new SongManager(songDAO);
        myTunesController.setSongManager(songManager);
    }

    public static void main(String[] args) {
        launch(args);

    }
}
