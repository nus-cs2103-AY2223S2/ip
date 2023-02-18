package exceptions;

/**
 * This class is an exception for Duke being unable to read any previous task list
 */
public class FileLoadException extends DukeException {

    /**
     * Creates an exception of Duke being unable to read the previous task list
     * due to read/write access
     *
     * @param fileString file path of previous task list txt file
     */
    public FileLoadException(String fileString) {
        super("WHAT?! its ur first time?! WELCOME WELCOME!\n\n"
                + "If this is a mistake, check your local read/write settings for \n        "
                + fileString
                + "\n then reboot Duke again. \n\nOtherwise, continue on with a new list! Enjoy!");
    }
}
