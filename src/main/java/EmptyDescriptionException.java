public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String taskType) {
        super("You cannot leave your " + taskType + " description blank!");
    }
}
