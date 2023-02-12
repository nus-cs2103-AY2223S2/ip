package duke.Exception;

/**
 * A NoSuchFileException a type of DukeException that is thrown when the file that is to be input/output
 * fails to exist.
 */
public class NoSuchFileException extends DukeException {

    /**
     * Constructor for the NoSuchFileException.
     * @param input The (invalid) file name.
     */
    public NoSuchFileException(String input) {
        super("No such file name" + input +  "exists! Sorry!");
    }

    @Override
    public String getExceptionType() {
        return "No such file";
    }
}
