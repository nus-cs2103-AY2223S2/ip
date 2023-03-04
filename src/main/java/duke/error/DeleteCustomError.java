package duke.error;

import duke.data.TypeOfTask;

/**
 * Tracks and returns the error from Delete commands
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class DeleteCustomError extends CustomError {
    /**
     * Default constructor
     */
    public DeleteCustomError() {
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
            return "You entered wrongly. Please try again!";
        default:
            return "Something went wrong here";
        }
    }
}
