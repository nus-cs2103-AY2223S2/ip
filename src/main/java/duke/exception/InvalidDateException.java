package duke.exception;

import duke.task.Task;

public class InvalidDateException extends DukeException {

    public InvalidDateException(String date) {
        super(String.format("%s is not of the form '%s'", date, Task.DT_INPUT_FORMAT));
    }

    @Override
    public String getExceptionName() {
        String name = String.format("Invalid Date");
        return name;
    }
}
