import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    String home = System.getProperty("user.home");
    java.nio.file.Path path = java.nio.file.Paths.get(home, "Documents", "kuromi.txt");
    private Kuromi kuromi = new Kuromi(path);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKuromi(kuromi);
            stage.show();
            if(kuromi.getExitStatus()) {
                stage.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
