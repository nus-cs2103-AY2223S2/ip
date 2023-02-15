package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A Graphical User Interface made using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("./ip/data", "DukeList.txt");

    /**
     * Stages the primary stage onto the screen.
     * This is the starting point of the application.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this.duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the entire application and
     * stores data of the tasks in the local hard drive
     * upon exiting the application.
     */
    @Override
    public void stop() {
        this.duke.store();
    }
}
