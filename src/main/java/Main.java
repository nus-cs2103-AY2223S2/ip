import java.io.IOException;

import duke.Duke;
import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("data/list.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/logo.png")));
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("CatBot");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
