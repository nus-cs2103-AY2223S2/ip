package duke.exceptions;

public class InvalidMarkInput extends DukeException {
    public InvalidMarkInput(String index) {
        super("☹ OOPS!!! I'm sorry, you have enter invalid task index " + index);
    }
}