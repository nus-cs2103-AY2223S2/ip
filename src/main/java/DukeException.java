import java.util.ArrayList;
import java.util.Arrays;

public class DukeException extends Exception{
    protected ArrayList<String> commandError = new ArrayList<>(
            Arrays.asList("todo", "deadline", "event", "missing details"));
    protected ArrayList<String> indexError = new ArrayList<>(
            Arrays.asList("mark", "unmark", "delete"));
    protected ArrayList<String> fileError = new ArrayList<>(
            Arrays.asList("file already exists", "creating new file",
                    "updating file", "saving changes to file", "file not found"));
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
        } else {
            this.errorMsg = "I'm sorry but I don't know what that means :(";
        }
    }

    @Override
    public String toString() {
        return "Oh no!! " + this.errorMsg;
    }
}
