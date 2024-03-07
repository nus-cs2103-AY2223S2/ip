package duke.Exceptions;

public class DuplicateException extends Exception {
    /**
     * The constructor for InvalidTimeFormatException
     */
    public DuplicateException() {
        super(String.format("    OOPS!!! There is already an existing task with that name! "));
    }

    @Override
    public String getMessage() {
        return "    OOPS!!! There is already an existing task with that name! ";
    }
}