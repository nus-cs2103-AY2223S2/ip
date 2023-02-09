package duke.exception;

/**
 * An exception thrown when Duke is unable to create or write to a given file.
 */
public class FilePermissionsException extends DukeException {

    /**
     * Initializes a FilePermissionsException that was caused by a file at the given
     * path.
     * 
     * @param path The path to the file that cannot be created/written to
     */
    public FilePermissionsException(String path) {
        super(path);
    }

    @Override
    public String getExceptionName() {
        return "Unable to create or write to file";
    }

}
