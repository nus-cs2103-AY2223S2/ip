package exceptions;

/**
 * This class is an exception for Duke attempting to import a previous task list from 
 * a file that does not follow Duke's standards
 */
public class LoadTaskException extends DukeException {

    /**
     * Creates an exception to let users know that Duke is unable to load the previous task list
     * due to errors within the file
     */
    public LoadTaskException() {
        super("OH NO! There was some issue with the previous list! \nDuke was unable to load it... :(");
    }
}
