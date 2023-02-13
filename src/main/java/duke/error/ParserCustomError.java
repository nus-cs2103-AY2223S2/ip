package duke.error;

import duke.data.TypeOfTask;

/**
 * Tracks and returns the error from Parser functions
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class ParserCustomError extends CustomError {
    /**
     * Default cosntructor
     */
    public ParserCustomError() {
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
            return "format of date given is not recognized";
        case 1:
            return "format of time given is not recognized";
        default:
            return "Something went wrong here";
        }
    }
}
