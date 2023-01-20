public class DukeWriteException extends DukeException{
    public DukeWriteException(String s) {
        super(String.format("\t OOPS!!! Failed to write to save file:\n\t   %s", s));
    }
}
