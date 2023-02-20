package Duke.Exceptions;

public class FileException extends DukeMainExceptions {
    private static final String ERROR_MESSAGE = "There is an error in importing the file";

    public FileException() {
        super(ERROR_MESSAGE);
    }
    @Override
    public String toString() {
        return ERROR_MESSAGE;
    }
}
