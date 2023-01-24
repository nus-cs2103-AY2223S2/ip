package duke.exceptions;

public class TaskNameNotSpecified extends TaskInitError {
    public TaskNameNotSpecified(String errorMessage) {
        super(errorMessage);
    }
}