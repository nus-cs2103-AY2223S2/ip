import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Leo using FXML.
 */
public class Main extends Application {
    //Solution adapted from https://se-education.org/guides/tutorials/javaFxPart4.html

    private Leo leo = new Leo();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLeo(leo);
            stage.show();

            stage.setOnCloseRequest(event -> {
                fxmlLoader.<MainWindow>getController().handleExit();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}