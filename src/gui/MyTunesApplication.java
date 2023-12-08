package gui;

import gui.controller.MyTunesController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyTunesApplication extends Application {

    @Override
<<<<<<< HEAD
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MyTunesApplication.class.getResource("view/myTunes.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 600);
        stage.setTitle("MyTunes");
        stage.setScene(scene);
        stage.show();
        MyTunesController myTunesController = fxmlLoader.getController();
        MyTunesModel model = new MyTunesModel();
        myTunesController.setModel(model);
=======
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/myTunes.fxml"));
        Parent root = loader.load();

        MyTunesController myTunesController = loader.getController();
        myTunesController.setModel(new MyTunesModel());
        myTunesController.setSongTableView(myTunesController.getSongTableView());

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My Tunes");
        primaryStage.show();

>>>>>>> revert(2hrs-ago)

    }

    public static void main(String[] args) {
        launch(args);
    }
}
