package exceptions.invalid;

public class File extends exceptions.DukeException {
    /**
     * Constructs an Invalid File Exception for the given filepath.
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