package duke.Exception;

public class InvalidEventException extends DukeException {

    /**
     * The constructor for an InvalidEventException.
     * @param input The (invalid) user input.
     */
    public InvalidEventException(String input) {
        super("Sorry, the end date/time of your event " + input
                + "is before the start date/time! Please try again.");
    }

    @Override
    public String getExceptionType() {
        return "Invalid Input(s)";
    }
}
