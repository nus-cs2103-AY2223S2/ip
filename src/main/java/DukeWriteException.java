public class DukeWriteException extends DukeException{
    public DukeWriteException() {
        super(String.format("\t OOPS!!! Failed to write to save file:\n"));
    }
}
