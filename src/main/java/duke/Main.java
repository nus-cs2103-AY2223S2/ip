package duke;

import java.io.File;
import java.io.IOException;

import duke.fxui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main process for starting the GUI for the chatbot.
 */
public class Main extends Application {
    /**
     *
     */
    private final Duke DUKE = new Duke("data" + File.separator + "tasks.txt");

    /**
     * Creates a GUI with title of duke chatbot. It loads the main gui scene of the chatbot and sets it up.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            VBox vBox = fxmlLoader.load();
            Scene scene = new Scene(vBox);
            stage.setScene(scene);
            stage.setTitle("Duke Chatbot");
            fxmlLoader.<MainWindow>getController().setDuke(DUKE);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
