package duke;
class TaskInitError extends Exception {
    public TaskInitError(String errorMessage) {
        super(errorMessage);
    }
}