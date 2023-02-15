package duke.exceptions;

public class CorruptedFileException extends DukeException {
    public CorruptedFileException(String errorMessage) {
        super(errorMessage);
    }
}
