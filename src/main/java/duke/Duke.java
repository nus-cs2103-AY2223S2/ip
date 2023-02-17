package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
/**
 * The main class of the software engineering project Duke.
 * This class is responsible for coordinating the work of the user interface, storage and parser classes.
 *
 * @author owen-yap
 */
public class Duke extends Application {
    private final Parser parser;
    private final Storage storage;
    private final Ui ui;

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
        System.out.println("here");
        stage.setScene(scene); // Setting the stage to show our screen
        System.out.println("here");
        stage.show(); // Render the stage.
        System.out.println("here");
    }
    /**
     * Constructs a Duke object which takes a file path and initializes the storage, user interface and parser.
     *
     * @param filePath the file path where tasks will be stored.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.parser = new Parser(storage, ui);
    }
    /**
     * The main method of the Duke class which initializes the program and runs it.
     */
    public void run() {
        this.ui.printWelcomeMsg();
        boolean hasNext = true;
        while (hasNext) {
            String input = this.ui.readInput();
            hasNext = this.parser.parse(input);
        }
    }
    /**
     * The entry point of the Duke program.
     *
     * @param args cli arguments.
     */
    public static void main(String[] args) {
        new Duke("data.txt").run();
    }
}
