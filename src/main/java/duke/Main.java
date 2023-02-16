package duke;

import java.io.IOException;
import duke.UI.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
//@@author se-education
//Reused from https://se-education.org/guides/tutorials/javaFxPart3.html
//with minor modifications
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("Chatbot Tim Proudly Made by Chengyue");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}