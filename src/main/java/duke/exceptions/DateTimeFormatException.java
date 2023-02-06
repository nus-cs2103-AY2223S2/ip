package duke.exceptions;

/**
 * The exception class that indicates that the DateTime format inputted is incorrect.
 *
 * @author JamesLiuZX
 *     AY2223-S2 CS2103T
 */
public class DateTimeFormatException extends DukeException {
    /**
     * The default constructor for this exception.
     */
    public DateTimeFormatException() {
        super("Please input the datetime format in /by DD/MM/YY HHMM.");
    }
}

