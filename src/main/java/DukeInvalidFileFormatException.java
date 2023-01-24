public class DukeInvalidFileFormatException extends DukeException {

    DukeInvalidFileFormatException() {
        super("The `duke.txt` file was found to be corrupt. Best effort attempt to load file from disk...");
    }
}
