package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * This class represents exceptions specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs DukeException when loading unrecognized tasks from the file.
     */
    public DukeException(String message) {
        System.out.println("☹ OOPS!!! Loading error occurred due to unrecognized task: " + message);
    }

    /**
     * Constructs DukeException when file could not be found for loading tasks.
     */
    public DukeException(FileNotFoundException e) {
        System.out.println("☹ OOPS!!! Loading error occurred since the file could not be found.");
    }

    /**
     * Constructs DukeException when loading invalid date time formatted tasks from the file.
     */
    public DukeException(DateTimeParseException e) {
        System.out.println("☹ OOPS!!! Loading error occurred due to incorrect date time format.");
        System.out.println("Format of the date should be (MMM dd yyyy h:mm a).");
    }

    /**
     * Constructs DukeException when tasks cannot be saved to the file.
     */
    public DukeException(IOException e) {
        System.out.println("☹ OOPS!!! Tasks could not be saved to the file.");
        System.out.println("The following error occurred " + e);
    }
}
