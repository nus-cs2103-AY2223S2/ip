package duke.error;

import duke.data.TypeOfTask;

/**
 * Tracks and returns the error from Mark commands
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class MarkCustomError extends CustomError {
    /**
     * Default constructor
     */
    public MarkCustomError() {
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
        switch(errorCode) {
        case 0:
            return String.format("Oops! The description of %s cannot be empty", task.toString());
        case 1:
            return "Please check if your inputs are valid.";
        default:
            return "Something went wrong here";
        }
    }
}
