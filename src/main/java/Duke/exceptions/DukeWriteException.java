package duke.exceptions;

public class DukeWriteException extends DukeException{
    public DukeWriteException() {
        super("\t OOPS!!! Failed to write to save file:\n");
    }
}
