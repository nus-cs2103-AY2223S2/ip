package duke;

import java.io.IOException;

import duke.controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 *
 * @author Haiqel Bin Hanaffi
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * Default constructor
     *
     * @throws Exception
     */
    public Main() throws Exception {
    }

    /**
     * Starts the creation of the UI using FXML
     *
     * @param stage the primary stage for this application, onto which
     *      the application scene can be set.
     *      Applications may create other stages, if needed, but they will not be
     *      primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            ap.getStylesheets().add("/css/MainWindow.css");
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("MakimaBot");
            stage.getIcons().add(new Image("/images/makimaIcon.jpg"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
