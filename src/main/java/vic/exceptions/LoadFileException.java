package vic.exceptions;
/**
 * A DukeException to check the error during loading of local file
 */
public class LoadFileException extends DukeException {
    /**
     * Constructor for LoadFileException
     *
     * @param errorMessage the error message
     * @param file         the file that cause the error
     */
    public LoadFileException(String file, String errorMessage) {
        super("☹ OOPS!!! Fail to load file: " + file + "\n" + errorMessage);
    }
}
