package duke.exceptions;

public class FormatException extends DukeException {
    public FormatException(String format) {
        super("☹ OOPS!!! I'm sorry, I don't understand your format! Try " + format + "!");
    }
}
