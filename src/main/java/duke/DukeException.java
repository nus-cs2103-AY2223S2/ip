package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * This class represents exceptions specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Prints error message for unknown command.
     */
    public DukeException() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints error message for empty task description.
     */
    public DukeException(String task) {
        System.out.println("☹ OOPS!!! The description of a " + task + " cannot be empty.");
    }

    /**
     * Prints error message for out of bounds index.
     */
    public DukeException(int index) {
        System.out.println("☹ OOPS!!! The index " + index + " for the list of tasks is out of bounds.");
    }

    /**
     * Prints error message for incorrect date time format.
     */
    public DukeException(DateTimeParseException e) {
        System.out.println("☹ OOPS!!! Incorrect date time format. Use dd/mm/yyyy HHmm instead.");
    }

    /**
     * Prints error message when tasks cannot be saved to the file.
     */
    public DukeException(IOException e) {
        System.out.println("☹ OOPS!!! The following error occurred " + e);
    }
}
