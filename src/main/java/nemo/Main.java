package nemo;

import nemo.ui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Nemo using FXML.
 */
public class Main extends Application {

    private Nemo nemo = new Nemo();

    @Override
    public void start(Stage stage) {
        System.out.println("test");
        AnchorPane ap = new MainWindow(nemo);
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.setTitle("Nemo - the task tracker");
        stage.getIcons().add(new Image(MainWindow.class.getResourceAsStream("/images/icon.png")));
        stage.show();
    }
}
