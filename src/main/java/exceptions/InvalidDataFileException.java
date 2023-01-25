package exceptions;

public class InvalidDataFileException extends DukeException {
    public InvalidDataFileException() {
        super("☹ OOPS!!! Could not read data from the provided source file.");
    }
}
