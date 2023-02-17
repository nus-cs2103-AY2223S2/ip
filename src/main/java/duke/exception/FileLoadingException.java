package duke.exception;

/**
 * Represents wrong deadline date format
 */
public class FileLoadingException extends DukeException {
    public FileLoadingException(String filePath) {
        super("WOOF! Boss! Something is wrong! I cannot load the file at path " + filePath + " :(");
    }
}
