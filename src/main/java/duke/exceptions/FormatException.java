package duke.exceptions;

/**
 * An error for when the date/times are formatted wrongly.
 */
public class FormatException extends DukeException {
    public FormatException(String format) {
        super("☹ OOPS!!! I'm sorry, I don't understand your format! Try " + format + "!");
    }

    public FormatException(String format, String customMsg) {
        super(
                "☹ OOPS!!! I'm sorry, I don't understand your format! Try "
                        + format + "!\n" + customMsg);
    }

}
