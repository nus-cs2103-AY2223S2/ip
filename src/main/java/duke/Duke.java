package duke;

import java.io.IOException;
import java.net.URL;

import duke.controllers.MainWindow;
import duke.exceptions.DukeException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * This class contains a Duke that contains the main method.
 */
public class Duke extends Application {

    private static final URL MAIN_WINDOW_FXML = Duke.class.getResource("/view/MainWindow.fxml");
    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private TextUi textUi;

    /**
     * A GUI for Duke using FXML.
     */
    @Override
    public void start(Stage stage) {
        textUi = new TextUi();
        parser = new Parser();
        storage = new Storage();
        taskList = storage.readSavedFile();
        loadMainWindow(stage);
    }

    private void loadMainWindow(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MAIN_WINDOW_FXML);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Generates a response to user input.
     * @return Response string.
     */
    public String getResponse(String input) {
        if (!input.equals("bye")) {
            return parser.parse(input, taskList, storage, textUi);
        } else {
            return textUi.getGoodbyeMessage();
        }
    }
}
