import java.util.ArrayList;
import java.util.Arrays;

public class DukeInputError extends Exception{
    protected String errorMessage;
    private static final ArrayList<String> taskInput = new ArrayList<> (Arrays.asList("todo", "list", "deadline", "event"));
    private static final ArrayList<String> markInput = new ArrayList<> (Arrays.asList("mark", "unmark"));


    public DukeInputError(String inputType) {
        if (taskInput.contains(inputType)) {
            this.errorMessage = String.format("The description of a %s cannot be empty", inputType);
        } else if (markInput.contains(inputType)) {
            this.errorMessage = String.format("I'm sorry, but please specify a valid task index to %s", inputType);
        } else {
            this.errorMessage = String.format("I'm sorry, but I don't know what that means :-(");
        }
    }

    @Override
    public String toString() {
        return "    ☹ OOPS!!! " + errorMessage;
    }
}
