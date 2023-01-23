public class EmptyTaskException extends DukeException {
    EmptyTaskException(String taskType) {
        super(String.format("The description of a %s task cannot be empty.", taskType));
    }
}
