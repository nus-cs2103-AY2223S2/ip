package duke;

import duke.exception.DukeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.Scanner;

/**
 * A task management program that reads user input to create and delete tasks.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor to create an instance of Duke.
     *
     * @param filePath The file path to where the list of tasks is stored.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = storage.readData();
    }

    /**
     * Starts the Duke program.
     */
    public void run() {
        Ui.welcomeMessage();
        Parser parser = new Parser(new Scanner(System.in));
        try {
            while (parser.notDone()) {
                tasks = parser.parse(tasks);
            }
        } catch (DukeException e) {
            System.out.println("ParseError: " + e);
        } finally {
            storage.writeData();
            Ui.farewellMessage();
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public static void main(String[] args) {
        new Duke("data.txt").run();
    }
}
