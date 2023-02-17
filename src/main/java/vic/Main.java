package vic;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Vic vic = new Vic("data/vic.tasks.txt");
    @FXML
    @Override
    public void start(Stage stage) {
        try {
            URL fxmlLocation = getClass().getResource("/view/MainWindow.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setVic(vic);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
