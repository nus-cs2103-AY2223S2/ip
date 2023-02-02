package exceptions;

/**
 * Represents exceptions for invalid, missing or corrupt data files.
 */
public class InvalidDataFileException extends DukeException {
    /**
     * Constructs an exception for invalid, missing or corrupt data files.
     */
    public InvalidDataFileException() {
        super("OOPS!!! Could not read data from the provided source file.");
    }
}
