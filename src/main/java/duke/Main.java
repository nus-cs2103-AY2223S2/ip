package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {
    private MainWindow mainWindowController;
    private Image iconImage = new Image(this.getClass().getResourceAsStream("/images/icon.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            stage.getIcons().add(iconImage);
            stage.setResizable(false);

            mainWindowController = (MainWindow)fxmlLoader.<MainWindow>getController();
            assert mainWindowController != null : "mainWindowController should not be null in start";

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        assert mainWindowController != null : "mainWindowController should not be null in stop";
        assert mainWindowController.getDuke() != null : "Duke should not be null in stop";
        mainWindowController.saveDukeData();
    }
}
