package duke;

import duke.exception.DukeException;
import java.util.Scanner;

/**
 * A task management program that reads user input to create and delete tasks.
 */
public class Duke {
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

}
