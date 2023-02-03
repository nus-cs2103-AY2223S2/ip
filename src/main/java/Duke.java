import java.io.IOException;
import java.time.DateTimeException;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import javafx.application.Platform;

/**
 * Main application class.
 */

public class Duke {
    private final Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates <case>Duke</case> object.
     *
     * @param filePath To specific a file path to save the previous records.
     */
    public Duke(String filePath) {
        ui = new Ui();

        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadRecord());
            tasks.printList();
            ui.printDashes();
        } catch (IOException e) {
            System.out.println("Error occurs when try to load.");
            tasks = new TaskList();
        }
    }

    /**
     * Main application loop.
     * Gets the user input to trigger the run.
     *
     * @param userInput User input.
     * @return Duke response to the user input respectively.
     */
    public String getResponse(String userInput) {
        String temp = "";

        if (userInput.equals("bye")) {
            Platform.exit();
        }

        try {
            temp = Parser.parseInput(tasks, userInput);
            storage.writeToFile(tasks);
            return temp;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "The operation must follow by a integer";
        } catch (IOException e) {
            return "Error occurs when try to access your file";
        } catch (DateTimeException e) {
            return "Invalid date time format, please try again!";
        }
    }
}
