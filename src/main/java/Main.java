import java.io.IOException;
import smudge.main.Smudge;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Smudge using FXML.
 */
public class Main extends Application {

    private final Smudge smudge = new Smudge();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSmudge(smudge);
            stage.show();
            stage.setResizable(false);
            stage.setTitle("Smudge");
        } catch (IOException e) {
            stage.close();
            e.printStackTrace();
        }
    }
}