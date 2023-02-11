package vic.exceptions;
/**
 * A DukeException to check the error during save the tasks to local file
 */
public class SaveFileException extends DukeException {
    /**
     * Constructor for SaveFileException
     *
     * @param errorMessage the error message
     * @param file         the file that cause the error
     */
    public SaveFileException(String file, String errorMessage) {
        super("☹ OOPS!!! Fail to save file: " + file + "\n" + errorMessage);
    }
}
