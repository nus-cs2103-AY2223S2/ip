package duke.error;

import duke.data.TypeOfTask;

/**
 * Represents error that tracks and returns the error from commands not recognized by Duke.
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class UnknownCustomError extends CustomError {
    /**
     * Default constructor
     */
    public UnknownCustomError() {
        super();
    }

    /**
     * Gets the corresponding error given by the error code.
     * This method is implemented from Error class
     *
     * @param errorCode Code of the error
     * @return Error message
     */
    public String getErrorMessage(TypeOfTask task, int errorCode) {
        return "Oops! I don't understand what it means!";
    }
}
