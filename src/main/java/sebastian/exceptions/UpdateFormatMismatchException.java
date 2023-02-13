package sebastian.exceptions;

/**
 * Exception when an update command is given in the wrong format
 */
public class UpdateFormatMismatchException extends InputFormatMismatchException {

    /**
     * Constructor
     */
    public UpdateFormatMismatchException() {
        super(
                "Please specify an update in the following format:" + "\n"
                        + "update [index of the task to be updated] [detail to be changed]"
                        + "For instance: suppose on the list we have : \n"
                        + "1. [D][ ] submit assignment (by: 30 Jan 2023 2359) \n"
                        + "You may update the due time by using the following command: \n"
                        + "update 1 /desc submit assignment 2 /by 2023-02-05 2359"
        );
    }
}
