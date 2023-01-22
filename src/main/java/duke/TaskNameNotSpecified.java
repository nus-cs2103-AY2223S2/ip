package duke;
class TaskNameNotSpecified extends TaskInitError {
    public TaskNameNotSpecified(String errorMessage) {
        super(errorMessage);
    }
}