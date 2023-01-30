package duke.exceptions;

public class InvalidFilePathException extends DukeExceptions {
    private static final String MESSAGE = "⚠ oops...we can't find where the file is\n";
    public InvalidFilePathException() {
        super(MESSAGE);
    }
}