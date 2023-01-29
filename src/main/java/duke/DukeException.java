package duke;

public class DukeException extends RuntimeException {
    public static final DukeException DATETIME_FORMAT = new DukeException("Only datetime format of 2023-01-01 is " + "accepted");

    public DukeException(String message) {
        super(message);
    }
}
