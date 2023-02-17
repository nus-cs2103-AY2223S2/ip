package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Solution below adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("data\\pandora.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            //scene.setFill(Color.ALICEBLUE);
            stage.setScene(scene);
            stage.setTitle("PandoraBot");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
