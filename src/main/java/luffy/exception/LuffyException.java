package luffy.exception;

import java.util.ArrayList;
import java.util.Arrays;

/** Class contains all exceptions thrown by Luffy. */
public class LuffyException extends Exception {
    protected ArrayList<String> commandError = new ArrayList<>(
            Arrays.asList("todo", "deadline", "event", "view", "missing details"));
    protected ArrayList<String> indexError = new ArrayList<>(
            Arrays.asList("mark", "unmark", "delete"));
    protected ArrayList<String> fileError = new ArrayList<>(
            Arrays.asList("file already exists", "creating new file",
                    "updating file", "saving changes to file", "file not found", "empty line in file"));
    protected ArrayList<String> formatError = new ArrayList<>(
            Arrays.asList("date format", "wrong order"));
    protected ArrayList<String> findError = new ArrayList<>(Arrays.asList("find", "empty keyword"));
    protected String errorMsg;

    /**
     * Creates an instance of LuffyException.
     * @param command type of exception thrown.
     */
    public LuffyException(String command) {
        if (commandError.contains(command)) {
            this.errorMsg = "The " + command + " description/date cannot be empty.";
        } else if (indexError.contains(command)) {
            this.errorMsg = "Please remember to enter an index!";
        } else if (fileError.contains(command)) {
            this.errorMsg = "An error occurred when/as " + command;
        } else if (findError.contains(command)) {
            this.errorMsg = "Find keyword cannot be empty!";
        } else {
            switch (command) {
            case "bounds":
                this.errorMsg = "This index is out of bounds!";
                break;
            case "timing":
                this.errorMsg = "The task timings cannot be empty!";
                break;
            case "missing details":
                this.errorMsg = "The task input is missing some details!";
                break;
            case "date format":
                this.errorMsg = "The date is not the correct format!\n"
                        + "Please enter a date with this format: yyyy-mm-dd";
                break;
            case "wrong order":
                this.errorMsg = "The dates entered are in the wrong order!\n"
                        + "The correct formats are as follows:\n"
                        + "deadline task_name/by yyyy-mm-dd\n"
                        + "event task_name/from yyyy-mm-dd/by yyyy-mm-dd";
                break;
            case "too many details":
                this.errorMsg = "The command shouldn't be followed by other details!";
                break;
            case "input type":
                this.errorMsg = "Input is the wrong type!!";
                break;
            default:
                this.errorMsg = "I'm sorry but I don't know what that means :(";
            }
        }
    }

    /**
     * Overrides toString and returns a String containing exception message.
     * @return String containing exception message.
     */
    @Override
    public String toString() {
        return "Oh no!! " + this.errorMsg;
    }
}
