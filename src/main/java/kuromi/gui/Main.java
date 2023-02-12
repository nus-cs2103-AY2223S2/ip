package kuromi.gui;

import java.io.IOException;
import java.nio.file.Files;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kuromi.Kuromi;
import kuromi.gui.component.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private String home = System.getProperty("user.dir");
    private java.nio.file.Path path = java.nio.file.Paths.get(home, "data", "kuromi.txt");
    private Kuromi kuromi;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            kuromi = new Kuromi(path, stage);
            fxmlLoader.<MainWindow>getController().setKuromi(kuromi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
