package exceptions.invalid;

/**
 * Thrown when an invalid file at given filepath has been provided.
 */
public class File extends exceptions.DukeException {
    /**
     * This method constructs an Invalid File Exception for the given filepath.
     *
     * Example:
     * User tries to access file that does not exist.
     *
     * @param filepath User supplied filepath
     */
    public File(String filepath) {
        super(String.format("%s Unable to find the file at %s", OOPS, filepath));
    }

}
