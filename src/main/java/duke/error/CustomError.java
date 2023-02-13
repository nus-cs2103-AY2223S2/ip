package duke.error;

import duke.data.TypeOfTask;

/**
 * Error class that will represent many types of errors encountered
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public abstract class CustomError {
    /**
     * Default constructor
     */
    public CustomError() {

    }
    public abstract String getErrorMessage(TypeOfTask typeOfTask, int errorCode);
}
