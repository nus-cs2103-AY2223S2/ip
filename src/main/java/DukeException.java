import java.util.*;

public class DukeException extends Exception{
    protected ArrayList<String> commandError = new ArrayList<>(
            Arrays.asList("todo", "deadline", "event"));
    protected ArrayList<String> indexError = new ArrayList<>(
            Arrays.asList("mark", "unmark", "delete"));
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
        } else {
            this.errorMsg = "I'm sorry but I don't know what that means :(";
        }
    }

    @Override
    public String toString() {
        return "Oh no!! " + this.errorMsg;
    }
}
