import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import munch.TaskList;
import munch.Ui;

import javax.swing.text.html.ImageView;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    String filePath = "src/main/java/data/SavedTaskList.txt";
    private Munch munch = new Munch(filePath);
    private Image muncher = new Image(this.getClass().getResourceAsStream("/images/levi.jpeg"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle(Ui.title());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMunch(munch);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}