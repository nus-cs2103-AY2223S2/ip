package duke;

import java.util.ArrayList;
import java.util.Arrays;

public class DukeException extends Exception{
    protected ArrayList<String> commandError = new ArrayList<>(
            Arrays.asList("todo", "deadline", "event", "missing details"));
    protected ArrayList<String> indexError = new ArrayList<>(
            Arrays.asList("mark", "unmark", "delete"));
    protected ArrayList<String> fileError = new ArrayList<>(
            Arrays.asList("file already exists", "creating new file",
                    "updating file", "saving changes to file", "file not found", "empty line in file"));
    protected ArrayList<String> formatError = new ArrayList<>(
            Arrays.asList("date format", "wrong order"));
    protected ArrayList<String> findError = new ArrayList<>(Arrays.asList("empty keyword"));
    protected String errorMsg;

    public DukeException(String command) {
        if (commandError.contains(command)) {
            this.errorMsg = "The task description cannot be empty.";
        } else if (indexError.contains(command)) {
            this.errorMsg = "Please remember to enter an index!";
        } else if (command.equals("bounds")) {
            this.errorMsg = "This index is out of bounds!";
        } else if (command.equals("timing")) {
            this.errorMsg = "The task timings cannot be empty!";
        } else if (command.equals("missing details")) {
            this.errorMsg = "The task input is missing some details!";
        } else if (fileError.contains(command)) {
            this.errorMsg =  "An error occurred when/as " + command;
        } else if (command.equals("date format")) {
            this.errorMsg = "The date is not the correct format!\n" +
                    "Please enter a date with this format: yyyy-mm-dd";
        } else if (command.equals("wrong order")) {
            this.errorMsg = "The dates entered are in the wrong order!\n" +
                    "The correct formats are as follows:\n" +
                        "deadline task_name/by yyyy-mm-dd\n" +
                            "event task_name/from yyyy-mm-dd/by yyyy-mm-dd";
        } else if (command.equals("too many details")) {
            this.errorMsg = "The command shouldn't be followed by other details!";
        } else if (command.equals("input type")) {
            this.errorMsg = "The input following the command is the wrong type!";
        } else if (findError.contains(command)) {
            this.errorMsg = "Find keyword cannot be empty!";
        } else {
            this.errorMsg = "I'm sorry but I don't know what that means :(";
        }
    }

    @Override
    public String toString() {
        return "Oh no!! " + this.errorMsg;
    }
}
