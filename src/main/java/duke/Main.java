package duke;

import java.io.IOException;

import gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for duke.Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    private Image icon = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Main() throws IOException {}

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke");
            stage.getIcons().add(icon);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            fxmlLoader.<MainWindow>getController().displayInitGreeting();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
