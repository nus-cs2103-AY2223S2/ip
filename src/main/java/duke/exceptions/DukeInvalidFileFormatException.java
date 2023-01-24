package duke.exceptions;

public class DukeInvalidFileFormatException extends DukeException {

    public DukeInvalidFileFormatException() {
        super("The `duke.txt` file was found to be corrupt");
    }
}
