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

    /**
     * Creates an abstract method to get the error message from Duke Exception.
     * This method must be implemented by all subclasses that represents
     * other types of errors.
     *
     * @param typeOfTask Type of task
     * @param errorCode Error code
     * @return Error message
     */
    public abstract String getErrorMessage(TypeOfTask typeOfTask, int errorCode);
}
