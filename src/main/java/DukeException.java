import java.util.ArrayList;
import java.util.Arrays;

public class DukeException extends Exception{
    protected ArrayList<String> commandError = new ArrayList<>(
            Arrays.asList("todo", "deadline", "event"));
    protected ArrayList<String> indexError = new ArrayList<>(
            Arrays.asList("mark", "unmark", "delete"));
    protected ArrayList<String> formatError = new ArrayList<>(
            Arrays.asList("date format"));
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
        } else if (formatError.contains(command)) {
            this.errorMsg = "The date is not the correct format!\n" +
                    "Please enter a date with this format: yyyy-mm-dd";
        } else {
            this.errorMsg = "I'm sorry but I don't know what that means :(";
        }
    }

    @Override
    public String toString() {
        return "Oh no!! " + this.errorMsg;
    }
}
