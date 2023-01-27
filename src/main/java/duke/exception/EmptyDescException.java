package duke.exception;

public class EmptyDescException extends DukeException {
    String taskType;
    public EmptyDescException(String taskType, String message) {
        super(message);
        this.taskType = taskType;
    }
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a " + this.taskType + " cannot be empty.";
    }
}
