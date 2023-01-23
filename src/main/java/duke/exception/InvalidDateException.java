package duke.exception;

import duke.task.Task;

/** An exception thrown when a user supplies dates in the wrong format. */
public class InvalidDateException extends DukeException {

    /**
     * Initializes an InvalidDateException that was caused by an unparseable string.
     * 
     * @param date The unparseable string that resulted in the exception
     */
    public InvalidDateException(String date) {
        super(String.format("%s is not of the form '%s'", date, Task.DT_INPUT_FORMAT));
    }

    @Override
    public String getExceptionName() {
        String name = String.format("Invalid Date");
        return name;
    }
}
