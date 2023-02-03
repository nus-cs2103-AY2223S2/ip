package tigerlily.exceptions;

public class InvalidFilePathException extends DukeExceptions {
    private static final String ERROR_MESSAGE = "⚠ oops...we can't find where the file is\n";
    public InvalidFilePathException() {
        super(ERROR_MESSAGE);
    }
}