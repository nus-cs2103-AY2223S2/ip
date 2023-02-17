package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A Main class that starts the Application.
 */
public class Main extends Application {
    private static final String FILE_PATH = "data/storage.txt";
    private static final String FOLDER_PATH = "data";

    private Duke duke = new Duke(FILE_PATH, FOLDER_PATH);

    /**
     * Starts the given stage.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            // @@author Jai2501-reused
            // Reused from https://github.com/Jai2501/ip/blob/master/src/main/java/retriever/Main.java
            // with minor modifications and mainly the portion where the author edit the text font style.
            scene.getRoot().setStyle("-fx-font-family: 'Arial'");
            // @@author MrTwit99
            stage.setScene(scene);
            stage.setTitle("The Best Helper in Town: Doraemon");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
