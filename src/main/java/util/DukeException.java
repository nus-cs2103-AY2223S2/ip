package util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * DukeException class to handle the different errors that can be encountered by the Duke Program.
 * @author Merrick
 */
public class DukeException extends Exception {
    private static final ArrayList<String> taskInput = new ArrayList<>(
            Arrays.asList("todo", "list", "deadline", "event"));
    private static final ArrayList<String> markInput = new ArrayList<>(Arrays.asList("mark", "unmark", "delete"));
    private String errorMessage = "I'm sorry, but I don't know what that means :-(";


    /**
     * Empty constructor for DukeException
     */
    public DukeException() {}

    /**
     * Constructor of DukeException.
     * @param inputType Description of error.
     */
    public DukeException(String inputType) {
        if (taskInput.contains(inputType)) {
            this.errorMessage = String.format("The description of a %s cannot be empty", inputType);
        } else if (markInput.contains(inputType)) {
            this.errorMessage = String.format("    Usage: '%s taskNumber'", inputType);
        } else if (inputType.equals("bounds")) {
            this.errorMessage = "I'm sorry, but please specify a valid task index.";
        } else {
            this.errorMessage = inputType;
        }
    }

    @Override
    public String toString() {
        return "    :( OOPS!!! " + errorMessage;
    }
}
