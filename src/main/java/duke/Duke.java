package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Represents a command-line to-do list interface,
 * capable of adding in todos, events and deadlines.
 */
public class Duke extends Application {
    private TaskList tasklist;

    private final Storage storage;
    private final Ui ui;

    private Parser parser;

    /**
     * Constructor
     */
    public Duke() {
        ui = new Ui();

        storage = new Storage("data/duke.txt");
        try {
            storage.checkFileExists();
            tasklist = storage.loadFile();
        } catch (DukeException e) {
            ui.showError(e);
        }

        parser = new Parser(tasklist, ui, storage);
    }


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public String getResponse(String input) {
        String output = parser.parse(input);
        return output;
    }


    /**
     * This is the main method that starts the Duke ToDoList command line interface.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Duke dukeList = new Duke();
    }
}
