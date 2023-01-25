// Exception that return a custom message that handles errors in task
// instructions
class TaskException extends Exception {
    public TaskException(String message) {
        super(message);
    }
}

