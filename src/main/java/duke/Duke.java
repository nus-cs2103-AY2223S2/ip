package duke;

import java.io.IOException;
import java.time.DateTimeException;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;

/**
 * Main application class.
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates <case>Duke</case> object.
     *
     * @param filePath To specific a file path to save the previous records.
     */

    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadRecord());
            tasks.printList();
        } catch (IOException e) {
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
        String temp;

        try {
            temp = Parser.parseInput(tasks, userInput);
            storage.writeToFile(tasks);
            return temp;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return numberFormatExceptionMessage();
        } catch (IOException e) {
            return ioExceptionMessage();
        } catch (DateTimeException e) {
            return dateTimeExceptionMessage();
        }
    }

    public String numberFormatExceptionMessage() {
        return "The operation must follow by a integer!";
    }

    public String ioExceptionMessage() {
        return "Error occurs when try to access your file";
    }

    public String dateTimeExceptionMessage() {
        return "Invalid date time format, please try again!";
    }
}


