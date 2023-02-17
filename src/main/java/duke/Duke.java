package duke;

import java.io.IOException;
import java.time.DateTimeException;

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
     * Gets the user input to trigger the run.
     * Main application loop.
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
        } catch (ArrayIndexOutOfBoundsException e) {
            return arrayIndexOutOfBoundsException();
        }
    }

    /**
     * Returns error message.
     * The operation must follow by an integer.
     *
     * @return Number format exception message.
     */
    public String numberFormatExceptionMessage() {
        return "The operation must follow by a integer!";
    }

    /**
     * Returns error message.
     * Error occurs when try to get access to the storage file.
     *
     * @return Error message.
     */
    public String ioExceptionMessage() {
        return "Error occurs when try to access your file";
    }

    /**
     * Returns error message.
     * User input date time is in the wrong format.
     *
     * @return Date time exception message.
     */
    public String dateTimeExceptionMessage() {
        return "Invalid date time format, please try again!";
    }

    /**
     * Returns error message.
     * User does not provide enough information that caused out of bounds.
     *
     * @return Array index out of bounds error message.
     */
    public String arrayIndexOutOfBoundsException() {
        return "Invalid format! Please ensure your input is in the "
                + "correct format!";
    }
}


