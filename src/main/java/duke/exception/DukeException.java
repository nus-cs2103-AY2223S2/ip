package duke.exception;
import duke.data.TypeOfTask;
import duke.error.CustomError;
import duke.error.DeadlineCustomError;
import duke.error.DeleteCustomError;
import duke.error.EventCustomError;
import duke.error.MarkCustomError;
import duke.error.ParserCustomError;
import duke.error.StorageCustomError;
import duke.error.TodoCustomError;
import duke.error.UnknownCustomError;
import duke.error.UnmarkCustomError;

/**
 * Duke Exception is a custom class that will handle common predictable errors made by the user.
 * Error Codes ranges from 0 to n, where n will be specified further in the future.
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class DukeException extends Exception {

    private String message;
    private TypeOfTask errorTask;
    private int errorCode;

    /**
     * Default constructor that will determine which messages will the exception print when error occurs.
     *
     * @param task Type of task
     * @param errorCode Error code
     */
    public DukeException(TypeOfTask task, int errorCode) {
        super();
        this.errorCode = errorCode;
        this.errorTask = task;
        CustomError error = new UnknownCustomError();
        switch(task) {
        case todo: {
            error = new TodoCustomError();
            break;
        }
        case deadline: {
            error = new DeadlineCustomError();
            break;
        }
        case event: {
            error = new EventCustomError();
            break;
        }
        case list: {
            break;
        }
        case mark: {
            error = new MarkCustomError();
            break;
        }
        case unmark: {
            error = new UnmarkCustomError();
            break;
        }
        case delete: {
            error = new DeleteCustomError();
            break;
        }
        case parser: {
            error = new ParserCustomError();
            break;
        }
        case storage: {
            error = new StorageCustomError();
            break;
        }
        default:
            break;
        }
        this.message = error.getErrorMessage(task, errorCode);
    }

    /**
     * Constructor for if a generic exception is thrown
     */
    public DukeException() {
        super();
        this.message = "Oops! I don't understand what it means!";
    }

    /**
     * Returns the error code for the specific error based on the type of task.
     *
     * @return error code
     */
    public int getErrorCode() {
        return this.errorCode;
    }

    /**
     * Gets the type of task that the command belongs to when error occurs.
     *
     * @return type of task
     */
    public TypeOfTask getErrorTask() {
        return this.errorTask;
    }

    /**
     * Returns the specific error message.
     *
     * @return error message
     */
    @Override
    public String getMessage() {
        return this.message;
    }

}
