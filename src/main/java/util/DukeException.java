package util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 */
public class DukeException extends Exception {
    protected String errorMessage = "I'm sorry, but I don't know what that means :-(";
    private static final ArrayList<String> taskInput = new ArrayList<>(Arrays.asList("todo", "list", "deadline", "event"));

    public DukeException(String inputType) {
        if (taskInput.contains(inputType)) {
            this.errorMessage = String.format("The description of a %s cannot be empty", inputType);
        } else {
            this.errorMessage = inputType;
        }
    }

    public DukeException() {}

    @Override
    public String toString() {
        return "    ☹ OOPS!!! " + errorMessage;
    }
}
