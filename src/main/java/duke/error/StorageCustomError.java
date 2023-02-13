package duke.error;

import duke.data.TypeOfTask;

/**
 * Tracks and returns the error from Storage functions
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class StorageCustomError extends CustomError {
    /**
     * Default constructor
     */
    public StorageCustomError() {
        super();
    }

    /**
     * Gets the corresponding error given by the error code.
     * This method is implemented from Error class
     * @param errorCode Code of the error
     * @return Error message
     */
    public String getErrorMessage(TypeOfTask task, int errorCode) {
        switch(errorCode) {
        case 0:
            return "Unable to retrieve data from file given. Please check if the file path is correct";
        case 1:
            return "Unable to save data to disk. Something went wrong";
        default:
            return "Something went wrong here";
        }
    }
}
